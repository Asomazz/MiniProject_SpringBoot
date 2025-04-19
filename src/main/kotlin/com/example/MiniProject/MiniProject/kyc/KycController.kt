package com.example.MiniProject.MiniProject.kyc

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class KycController(
    private val kycService: KycService
) {

    @PostMapping("/users/v1/kyc")
    fun createProfile(@RequestBody request: ProfileRequest): ResponseEntity<Any> {
        return try {
           kycService.createProfile(request)
            ResponseEntity.ok(request)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }

    }

    @GetMapping("/users/v1/kyc/{userId}")
    fun getKycByUserId(@PathVariable userId: Long): ResponseEntity<Any> {
        return try {
            val response = kycService.getKycByUserId(userId)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

}