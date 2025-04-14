package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
class AccountController(
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/accounts/v1/accounts")
    fun createAccount(@RequestBody request: AccountRequest) {
        var user = userRepository.findById(request.user_id).orElseThrow();
        val newAccount = AccountEntity(
            user = user,
            balance = request.balance,
            is_active = request.is_active,
            accountNumber = request.accountNumber
        )
        accountRepository.save(newAccount)
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
    var user_id: Long,
    var accountNumber: String,
    var balance: Float,
    var is_active: Boolean
)

data class AccountDTO(
    val id: Long?,
    val accountNumber: String,
    val balance: Float,
    val is_active: Boolean
)


