package com.example.MiniProject.MiniProject.users

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register(request: UserRequest) {

        if (request.username.isBlank()){
            throw IllegalArgumentException("Username must not be blank!")
        }

        if (request.password.length < 8) {
            throw IllegalArgumentException("Password must be at least 8 characters long")
        }

        val newUser = UserEntity(
            username = request.username,
            password = request.password
        )
        userRepository.save(newUser)
    }
}