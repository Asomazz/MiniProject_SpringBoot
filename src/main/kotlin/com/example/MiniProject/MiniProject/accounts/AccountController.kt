package com.example.MiniProject.MiniProject.accounts

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/accounts/v1/accounts")
    fun createAccount(@RequestBody request: AccountRequest): AccountResponse {
        return accountService.createAccount(request)
    }

    @PostMapping("/accounts/v1/accounts/{accountNumber}/close")
    fun closeAccount(@PathVariable accountNumber: String) {
        accountService.closeAccount(accountNumber)
    }

    @GetMapping("/accounts/v1/accounts")
    fun listAccounts(): Map<String, List<AccountDTO>> {
        return accountService.listAccounts()
    }
}
