package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository
) {

    fun createAccount(request: AccountRequest): AccountResponse {
        val user = userRepository.findById(request.userId).orElseThrow {
            IllegalArgumentException("User with ID ${request.userId} not found")
        }

        val newAccount = AccountEntity(
            user = user,
            accountNumber = generateAccountNumber(),
            balance = request.initialBalance
        )

        accountRepository.save(newAccount)

        return AccountResponse(
            userId = user.id!!,
            accountNumber = newAccount.accountNumber,
            initialBalance = request.initialBalance
        )
    }

    fun closeAccount(accountNumber: String) {
        val account = accountRepository.findByAccountNumber(accountNumber)
            ?: throw NoSuchElementException("Account not found")

        if (!account.isActive) {
            throw IllegalStateException("Account is already closed!")
        }

        account.isActive = false
        accountRepository.save(account)
    }

    fun listAccounts(): Map<String, List<AccountDTO>> {
        val accounts = accountRepository.findAll().map {
            AccountDTO(it.id, it.accountNumber, it.balance, it.isActive)
        }
        return mapOf("accounts" to accounts)
    }

    private fun generateAccountNumber(): String {
        return (1000000000L..9999999999L).random().toString()
    }
}
