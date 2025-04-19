package com.example.MiniProject.MiniProject.accounts

import org.springframework.http.ResponseEntity
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
    fun createAccount(@RequestBody request: AccountRequest): ResponseEntity<Any> {
        return try {
            val response = accountService.createAccount(request)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/accounts/v1/accounts/{accountNumber}/close")
    fun closeAccount(@PathVariable accountNumber: String): ResponseEntity<Any> {
        return try {
            accountService.closeAccount(accountNumber)
            ResponseEntity.ok("Account successfully closed!")
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(404).body(e.message)
        } catch (e: IllegalStateException) {
            ResponseEntity.status(409).body(e.message) //409 means conflict
        }
    }

    @GetMapping("/accounts/v1/accounts")
    fun listAccounts(): Map<String, List<AccountDTO>> {
        return accountService.listAccounts()
    }
}
