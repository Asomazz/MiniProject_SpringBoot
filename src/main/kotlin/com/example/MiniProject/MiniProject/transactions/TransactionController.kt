package com.example.MiniProject.MiniProject.transactions

import com.example.MiniProject.MiniProject.accounts.AccountRepository
import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
class TransactionController(
    val transactionRepository: TransactionRepository,
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/transfer")
    fun transfer(@RequestBody request: TransferRequest) {
        val source_account = accountRepository.findById(request.source_account_id).orElseThrow()
        val destination_account = accountRepository.findById(request.destination_account_id).orElseThrow()
        if (source_account.balance < request.amount) {
            throw IllegalArgumentException("Insufficient funds") //IllegalArgumentException is usually used for 400 bad request errors
        }
        source_account.balance -= request.amount
        destination_account.balance += request.amount
        accountRepository.save(source_account)
        accountRepository.save(destination_account)
        val newTransaction = TransactionEntity(
            source_account = source_account,
            destination_account = destination_account,
            amount = request.amount
        )
        transactionRepository.save(newTransaction)
    }
}
data class TransferRequest(
    val source_account_id: Long,
    val destination_account_id: Long,
    val amount: Float
)