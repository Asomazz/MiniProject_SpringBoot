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

}

data class AccountRequest(
    var user_id: Long,
    var account_number: String,
    var balance: Float,
    var is_active: Boolean
)


//@PostMapping("/account")
//fun createAccount(@RequestBody request: AccountRequest): ResponseEntity<String> {
//    return try {
//        val user = userRepository.findById(request.user_id).orElseThrow()
//        val newAccount = AccountEntity(
//            user = user,
//            balance = request.balance,
//            is_active = request.is_active,
//            account_number = request.account_number
//        )
//        accountRepository.save(newAccount)
//        ResponseEntity.ok("Account created successfully")
//    } catch (e: Exception) {
//        e.printStackTrace()
//        ResponseEntity.status(500).body("Server error: ${e.message}")
//    }
//}
