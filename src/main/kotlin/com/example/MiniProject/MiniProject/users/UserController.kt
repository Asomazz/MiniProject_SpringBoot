package com.example.MiniProject.MiniProject.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    val userRepository: UserRepository,
) {
    @PostMapping("/register")
    fun register(@RequestBody request: UserRequest) {
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