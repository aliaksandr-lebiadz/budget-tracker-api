package org.alebiadz.budget.tracker.service.bank.impl

import org.alebiadz.budget.tracker.commons.exception.AlreadyExistsException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.commons.exception.ObjectNullException
import org.alebiadz.budget.tracker.domain.entity.BankEntity
import org.alebiadz.budget.tracker.domain.repository.BankRepository
import org.alebiadz.budget.tracker.dto.bank.BankDto
import org.alebiadz.budget.tracker.service.bank.BankService
import org.alebiadz.budget.tracker.service.utils.copy
import org.alebiadz.budget.tracker.service.utils.toDto
import org.alebiadz.budget.tracker.service.utils.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BankServiceImpl(private val repository: BankRepository) : BankService {

    override fun getBanks(): List<BankDto> {

        return repository.findAllOrdered().map { it.toDto() }
    }

    override fun addBank(bank: BankDto): Long {

        validateUniqueness(bank)

        val entity = bank.toEntity().let { repository.save(it) }
        return entity.id!!
    }

    override fun changeBank(bank: BankDto) {

        val id = bank.id ?: throw ObjectNullException("id")
        val entity = repository.findByIdOrNull(id)
            ?.also { validateUniqueness(bank, it) }
            ?.copy(bank)
            ?: throw NotFoundException(id, BankEntity::class)
        repository.save(entity)
    }

    override fun deleteBankById(id: Long) {

        repository.deleteById(id)
    }

    private fun validateUniqueness(bank: BankDto, entity: BankEntity? = null) {

        val nameChanged = bank.name != entity?.name
        val existsByName = nameChanged && repository.existsByName(bank.name)
        if (existsByName) {
            throw AlreadyExistsException("Bank with name [${bank.name}] already exists")
        }
    }
}
