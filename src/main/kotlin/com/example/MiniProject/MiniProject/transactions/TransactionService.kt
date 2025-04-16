package com.example.MiniProject.MiniProject.transactions

import com.example.MiniProject.MiniProject.accounts.AccountRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    fun transfer(request: TransferRequest): Map<String, BigDecimal> {
        val source_account = accountRepository.findByAccountNumber(request.sourceAccountNumber)
            ?: throw IllegalArgumentException("Source account not found")

        val destination_account = accountRepository.findByAccountNumber(request.destinationAccountNumber)
            ?: throw IllegalArgumentException("Destination account not found")

        if (source_account.balance < request.amount.toFloat()) {
            throw IllegalArgumentException("Insufficient funds") //validation point 4
        }

        source_account.balance -= request.amount.toFloat()
        destination_account.balance += request.amount.toFloat()

        accountRepository.save(source_account)
        accountRepository.save(destination_account)

        val newTransaction = TransactionEntity(
            source_account = source_account,
            destination_account = destination_account,
            amount = request.amount.toFloat()
        )
        transactionRepository.save(newTransaction)

        return mapOf("newBalance" to source_account.balance.toBigDecimal())
    }
}