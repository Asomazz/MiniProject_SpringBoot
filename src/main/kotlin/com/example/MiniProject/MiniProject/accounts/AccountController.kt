package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
class AccountController(
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/account")
    fun createAccount(@RequestBody request: AccountRequest) {
        var user = userRepository.findById(request.user_id).orElseThrow();
        val newAccount = AccountEntity(
            user = user,
            balance = request.balance,
            is_active = request.is_active,
            account_number = request.account_number
        )
        accountRepository.save(newAccount)
    }

    @PutMapping("/account/{id}/close")
    fun closeAccount(@PathVariable id: Long) {
        val account = accountRepository.findById(id).orElseThrow()
        account.is_active = false
        accountRepository.save(account)
    }

    @GetMapping("accounts/v1/accounts")
    fun listAcounts(): Map<String, List<AccountDTO>> {
        val accounts = accountRepository.findAll().map {
            AccountDTO(it.id, it.account_number, it.balance, it.is_active)
        }
        return mapOf("accounts" to accounts)
    }

}

data class AccountRequest(
    var user_id: Long,
    var account_number: String,
    var balance: Float,
    var is_active: Boolean
)

data class AccountDTO(
    val id: Long?,
    val account_number: String,
    val balance: Float,
    val is_active: Boolean
)


