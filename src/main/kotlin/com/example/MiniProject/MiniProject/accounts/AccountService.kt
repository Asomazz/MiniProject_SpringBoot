package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

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
            balance = request.initialBalance.toFloat(),
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
            ?: throw IllegalArgumentException("Account not found")

        account.is_active = false
        accountRepository.save(account)
    }

    fun listAccounts(): Map<String, List<AccountDTO>> {
        val accounts = accountRepository.findAll().map {
            AccountDTO(it.id, it.accountNumber, it.balance, it.is_active)
        }
        return mapOf("accounts" to accounts)
    }

    private fun generateAccountNumber(): String {
        return (1000000000L..9999999999L).random().toString()
    }
}
