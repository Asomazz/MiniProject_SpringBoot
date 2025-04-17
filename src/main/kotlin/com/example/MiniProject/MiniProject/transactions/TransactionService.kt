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
        val sourceAccount = accountRepository.findByAccountNumber(request.sourceAccountNumber)
            ?: throw IllegalArgumentException("Source account not found")

        val destinationAccount = accountRepository.findByAccountNumber(request.destinationAccountNumber)
            ?: throw IllegalArgumentException("Destination account not found")

        if (sourceAccount.balance < request.amount) {
            throw IllegalArgumentException("Insufficient funds") //validation point 4
        }

        sourceAccount.balance -= request.amount
        destinationAccount.balance += request.amount

        accountRepository.save(sourceAccount)
        accountRepository.save(destinationAccount)

        val newTransaction = TransactionEntity(
            sourceAccount = sourceAccount,
            destinationAccount = destinationAccount,
            amount = request.amount
        )
        transactionRepository.save(newTransaction)

        return mapOf("newBalance" to sourceAccount.balance)
    }
}