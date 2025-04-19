package com.example.MiniProject.MiniProject.users

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/users/v1/register")
    fun register(@RequestBody request: UserRequest): ResponseEntity<Any> {
        return try {
            userService.register(request)
            ResponseEntity.ok("User registered successfully!")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }

    }
}
