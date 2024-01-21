package org.alebiadz.budget.tracker.service.currency.impl

import org.alebiadz.budget.tracker.commons.exception.AlreadyExistsException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.commons.exception.ObjectNullException
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.domain.repository.CurrencyRepository
import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto
import org.alebiadz.budget.tracker.service.account.AccountAdminService
import org.alebiadz.budget.tracker.service.currency.CurrencyService
import org.alebiadz.budget.tracker.service.utils.copy
import org.alebiadz.budget.tracker.service.utils.toDto
import org.alebiadz.budget.tracker.service.utils.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CurrencyServiceImpl(private val repository: CurrencyRepository, private val accountAdminService: AccountAdminService) : CurrencyService {

    override fun getCurrencies(): List<CurrencyDto> {

        return repository.findAll().map { it.toDto() }
    }

    override fun addCurrency(currency: CurrencyDto): Long {

        validateUniqueness(currency)

        val entity = currency.toEntity().let { repository.save(it) }
        return entity.id!!
    }

    override fun changeCurrency(currency: CurrencyDto) {

        val id = currency.id ?: throw ObjectNullException("id")
        val entity = repository.findByIdOrNull(id)
            ?.also { validateUniqueness(currency, it) }
            ?.copy(currency)
            ?: throw NotFoundException(id, CurrencyEntity::class)
        repository.save(entity)
    }

    @Transactional
    override fun deleteCurrencyById(id: Long) {

        val accountsFilter = AccountFilter.Builder().currencyId(id).build()
        val accountIds = accountAdminService.getAccounts(accountsFilter).map { it.id }.toSet()

        accountAdminService.deleteAccounts(accountIds)
        repository.deleteById(id)
    }

    private fun validateUniqueness(currency: CurrencyDto, entity: CurrencyEntity? = null) {

        val nameChanged = currency.name != entity?.name
        val codeChanged = currency.code != entity?.code
        val existsByName = nameChanged && repository.existsByName(currency.name)
        val existsByCode = codeChanged && repository.existsByCode(currency.code)
        if (existsByName && existsByCode) {
            throw AlreadyExistsException("Currency with name [${currency.name}] and code [${currency.code}] already exists")
        } else if (existsByName) {
            throw AlreadyExistsException("Currency with name [${currency.name}] already exists")
        } else if (existsByCode) {
            throw AlreadyExistsException("Currency with code [${currency.code}] already exists")
        }
    }
}