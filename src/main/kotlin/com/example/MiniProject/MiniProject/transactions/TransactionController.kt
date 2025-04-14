package com.example.MiniProject.MiniProject.transactions

import com.example.MiniProject.MiniProject.accounts.AccountRepository
import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
class TransactionController(
    val transactionRepository: TransactionRepository,
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/accounts/v1/accounts/transfer")
    fun transfer(@RequestBody request: TransferRequest): Map<String, BigDecimal> {
        val source_account = accountRepository.findByAccountNumber(request.sourceAccountNumber)
            ?: throw IllegalArgumentException("Source account not found")

        val destination_account = accountRepository.findByAccountNumber(request.destinationAccountNumber)
            ?: throw IllegalArgumentException("Destination account not found")

        if (source_account.balance < request.amount.toFloat()) {
            throw IllegalArgumentException("Insufficient funds") //IllegalArgumentException is usually used for 400 bad request errors
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
data class TransferRequest(
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val amount: BigDecimal
)