package com.example.MiniProject.MiniProject.kyc

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
    fun createProfile(@RequestBody request: ProfileRequest): ProfileRequest {
        return kycService.createProfile(request)

    }

    @GetMapping("/users/v1/kyc/{userId}")
    fun getKycByUserId(@PathVariable userId: Long): KycResponse {
        return kycService.getKycByUserId(userId)
    }

}