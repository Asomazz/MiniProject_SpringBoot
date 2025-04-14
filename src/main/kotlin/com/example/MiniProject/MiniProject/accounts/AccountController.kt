package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
class AccountController(
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/accounts/v1/accounts")
    fun createAccount(@RequestBody request: AccountRequest): AccountResponse {
        var user = userRepository.findById(request.userId).orElseThrow {
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

    private fun generateAccountNumber(): String {
        return (1000000000L..9999999999L).random().toString()
    }

    @PostMapping("/accounts/v1/accounts/{accountNumber}/close")
    fun closeAccount(@PathVariable accountNumber: String) {
        val account = accountRepository.findByAccountNumber(accountNumber)
            ?: throw IllegalArgumentException("Account not found")
        account.is_active = false
        accountRepository.save(account)
    }

    @GetMapping("accounts/v1/accounts")
    fun listAcounts(): Map<String, List<AccountDTO>> {
        val accounts = accountRepository.findAll().map {
            AccountDTO(it.id, it.accountNumber, it.balance, it.is_active)
        }
        return mapOf("accounts" to accounts)
    }
}

data class AccountRequest(
    var userId: Long,
    var accountNumber: String,
    var initialBalance: BigDecimal,
)

data class AccountResponse(
    var userId: Long,
    var accountNumber: String,
    var initialBalance: BigDecimal
)

data class AccountDTO(
    val id: Long?,
    val accountNumber: String,
    val balance: Float,
    val is_active: Boolean
)


