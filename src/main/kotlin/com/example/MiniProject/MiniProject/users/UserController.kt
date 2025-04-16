package com.example.MiniProject.MiniProject.users

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/users/v1/register")
    fun register(@RequestBody request: UserRequest) {
        userService.register(request)
    }
}
