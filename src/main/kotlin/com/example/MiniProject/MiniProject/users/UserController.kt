package com.example.MiniProject.MiniProject.users

import com.example.MiniProject.MiniProject.accounts.AccountRepository
import com.example.MiniProject.MiniProject.kyc.KycRepository
import com.example.MiniProject.MiniProject.transactions.TransactionRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller(
    val userRepository: UserRepository,
    val kycRepository: KycRepository,
    val transactionRepository: TransactionRepository,
    val accountRepository: AccountRepository
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody request: UserRequest) {
        val newUser = UserEntity(
            username = request.username,
            password = request.password
        )
        userRepository.save(newUser)
        }




}

data class UserRequest(
    val username: String,
    val password: String,
)