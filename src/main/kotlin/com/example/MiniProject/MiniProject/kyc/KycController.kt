package com.example.MiniProject.MiniProject.kyc

import com.example.MiniProject.MiniProject.users.UserEntity
import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate

@RestController
class KycController(
    val kycRepository: KycRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/users/v1/kyc")
    fun createProfile(@RequestBody request: ProfileRequest): ProfileRequest {
        var user = userRepository.findById(request.user_id).orElseThrow();
        var newProfile = KycEntity(
            user = user,
            date_of_birth = request.date_of_birth,
            nationality = request.nationality,
            salary = request.salary.toFloat()
        )
        kycRepository.save(newProfile)

        return ProfileRequest(
            user_id = newProfile.user.id!!,
            date_of_birth = newProfile.date_of_birth,
            nationality = newProfile.nationality,
            salary = BigDecimal.valueOf(newProfile.salary.toDouble())
        )
    }

    @GetMapping("/users/v1/kyc/{userId}")
    fun getKycByUserId(@PathVariable userId: Long): KycResponse {
        val kyc = kycRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("KYC not found for user ID: $userId")

        return KycResponse(
            userId = kyc.user.id!!,
            date_of_birth = kyc.date_of_birth
        )
    }

}

data class ProfileRequest(
    var user_id: Long,
    val date_of_birth: LocalDate,
    val nationality: String,
    val salary: BigDecimal
)

data class KycResponse(
    val userId: Long,
    val date_of_birth: LocalDate
)
