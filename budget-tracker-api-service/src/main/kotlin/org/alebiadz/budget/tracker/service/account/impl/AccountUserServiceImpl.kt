package org.alebiadz.budget.tracker.service.account.impl

import org.alebiadz.budget.tracker.commons.exception.AlreadyExistsException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.commons.exception.ObjectNullException
import org.alebiadz.budget.tracker.domain.entity.AccountEntity
import org.alebiadz.budget.tracker.domain.entity.AccountType
import org.alebiadz.budget.tracker.domain.entity.BankEntity
import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.domain.repository.AccountRepository
import org.alebiadz.budget.tracker.domain.repository.BankRepository
import org.alebiadz.budget.tracker.domain.repository.CardTypeRepository
import org.alebiadz.budget.tracker.domain.repository.CurrencyRepository
import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.dto.account.card.CardAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.card.CardAccountResponseDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountResponseDto
import org.alebiadz.budget.tracker.service.account.AccountUserService
import org.alebiadz.budget.tracker.service.specification.AccountSpecification
import org.alebiadz.budget.tracker.service.utils.UserUtils
import org.alebiadz.budget.tracker.service.utils.copy
import org.alebiadz.budget.tracker.service.utils.toCardDto
import org.alebiadz.budget.tracker.service.utils.toCashDto
import org.alebiadz.budget.tracker.service.utils.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountUserServiceImpl(
    private val accountRepository: AccountRepository,
    private val bankRepository: BankRepository,
    private val currencyRepository: CurrencyRepository,
    private val cardTypeRepository: CardTypeRepository,
) : AccountUserService {

    override fun getCardAccounts(filter: AccountFilter): List<CardAccountResponseDto> {

        val userId = UserUtils.getCurrentUserId()
        val specification = AccountSpecification(filter, AccountType.CARD, userId)
        return accountRepository.findAll(specification).map { it.toCardDto() }
    }

    override fun getCashAccounts(): List<CashAccountResponseDto> {

        val userId = UserUtils.getCurrentUserId()
        val filter = AccountFilter.Builder().build()
        val specification = AccountSpecification(filter, AccountType.CASH, userId)
        return accountRepository.findAll(specification).map { it.toCashDto() }
    }

    override fun addCardAccount(account: CardAccountRequestDto): Long {

        val userId = UserUtils.getCurrentUserId()
        validateUniqueness(userId, account)

        val bankEntity = loadBank(account.bankId)
        val currencyEntity = loadCurrency(account.currencyId)
        val cardTypeEntity = loadCardType(account.cardTypeId)

        val entity = account.toEntity(userId, bankEntity, currencyEntity, cardTypeEntity).let { accountRepository.save(it) }
        return entity.id!!
    }

    override fun addCashAccount(account: CashAccountRequestDto): Long {

        val userId = UserUtils.getCurrentUserId()
        validateUniqueness(userId, account)

        val currencyEntity = loadCurrency(account.currencyId)
        val entity = account.toEntity(userId, currencyEntity).let { accountRepository.save(it) }
        return entity.id!!
    }

    override fun changeCardAccount(account: CardAccountRequestDto) {

        val userId = UserUtils.getCurrentUserId()

        val id = account.id ?: throw ObjectNullException("id")
        val entity = accountRepository.findByIdAndUserIdAndType(id, userId, AccountType.CARD)
            ?.also { validateUniqueness(userId, account, it) }
            ?.let {
                val bankEntity = loadBank(account.bankId)
                val currencyEntity = loadCurrency(account.currencyId)
                val cardTypeEntity = loadCardType(account.cardTypeId)
                it.copy(account, bankEntity, currencyEntity, cardTypeEntity)
            }
            ?: throw NotFoundException(id, AccountEntity::class)
        accountRepository.save(entity)
    }

    override fun changeCashAccount(account: CashAccountRequestDto) {

        val userId = UserUtils.getCurrentUserId()

        val id = account.id ?: throw ObjectNullException("id")
        val entity = accountRepository.findByIdAndUserIdAndType(id, userId, AccountType.CASH)
            ?.also { validateUniqueness(userId, account, it) }
            ?.let {
                val currencyEntity = loadCurrency(account.currencyId)
                it.copy(account, currencyEntity)
            }
            ?: throw NotFoundException(id, AccountEntity::class)
        accountRepository.save(entity)
    }

    @Transactional
    override fun deleteAccountById(id: Long) {

        val userId = UserUtils.getCurrentUserId()

        accountRepository.deleteByIdAndUserId(id, userId)
    }

    private fun validateUniqueness(userId: Long, account: CardAccountRequestDto, entity: AccountEntity? = null) {

        val nameChanged = account.name != entity?.name
        val cardNumberChanged = account.cardNumber != entity?.cardNumber
        val existsByName = nameChanged && accountRepository.existsByUserIdAndName(userId, account.name)
        val existsByCardNumber = cardNumberChanged && accountRepository.existsByUserIdAndCardNumber(userId, account.cardNumber)
        if (existsByName && existsByCardNumber) {
            throw AlreadyExistsException("Card with name [${account.name}] and number [${account.cardNumber}] already exists")
        } else if (existsByName) {
            throw AlreadyExistsException("Card with name [${account.name}] already exists")
        } else if (existsByCardNumber) {
            throw AlreadyExistsException("Card with number [${account.cardNumber}] already exists")
        }
    }

    private fun validateUniqueness(userId: Long, account: CashAccountRequestDto, entity: AccountEntity? = null) {

        val currencyChanged = account.currencyId != entity?.currency?.id
        val existsByCurrency = currencyChanged && accountRepository.existsByUserIdAndCurrencyIdAndType(userId, account.currencyId, AccountType.CASH)
        if (existsByCurrency) {
            throw AlreadyExistsException("Cash with currency id [${account.currencyId}] already exists")
        }
    }

    private fun loadBank(bankId: Long): BankEntity {

        return bankRepository.findByIdOrNull(bankId) ?: throw NotFoundException(bankId, BankEntity::class)
    }

    private fun loadCurrency(currencyId: Long): CurrencyEntity {

        return currencyRepository.findByIdOrNull(currencyId) ?: throw NotFoundException(currencyId, CurrencyEntity::class)
    }

    private fun loadCardType(cardTypeId: Long): CardTypeEntity {

        return cardTypeRepository.findByIdOrNull(cardTypeId) ?: throw NotFoundException(cardTypeId, CardTypeEntity::class)
    }
}