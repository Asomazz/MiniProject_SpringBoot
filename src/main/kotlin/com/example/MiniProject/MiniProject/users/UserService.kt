package com.example.MiniProject.MiniProject.users

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register(request: UserRequest) {
        val newUser = UserEntity(
            username = request.username,
            password = request.password
        )
        userRepository.save(newUser)
    }
}