package com.example.MiniProject.MiniProject.users

import org.springframework.http.ResponseEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class UserController(
    private val userService: UserService
) {

    @Value("\${server-welcome-message}")
    val welcomeMessage: String = ""

    @Value("\${company-name}")
    val companyName: String = ""

    @Value("\${feature-festive-mode:false}")
    val isFestiveMode: Boolean = false

    @Value("\${festive-message:Welcome to Online banking!}")
    val festiveMessage: String = ""

    @GetMapping("/hello")
    fun helloWorld() =  "Hello World $welcomeMessage"

    @GetMapping("/company")
    fun companyName() =  "Welcome to Online Banking by $companyName"

    @PostMapping("/users/v1/register")
    fun register(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<Any> {
        return try {
            val response = userService.register(authRequest)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        } catch (e: BadCredentialsException) {
        ResponseEntity.status(401).body(e.message)
    }

    }

    @PostMapping("/users/v1/login")
    fun login(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<Any> {
        return try {
            val response = userService.login(authRequest)
            ResponseEntity.ok(response)
        } catch (e: BadCredentialsException) {
            ResponseEntity.status(401).body(mapOf("error" to e.message))
        }
    }
}
