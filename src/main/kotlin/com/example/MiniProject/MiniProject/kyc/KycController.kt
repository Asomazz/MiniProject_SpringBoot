package com.example.MiniProject.MiniProject.kyc

import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*


@RestController
class KycController(
    val kycRepository: KycRepository,
    val userRepository: UserRepository,
) {

    @PostMapping("/kyc")
    fun createProfile(@RequestBody request: ProfileRequest) {
        var user = userRepository.findById(request.user_id).orElseThrow();
        var newProfile = KycEntity(
            user = user,
            date_of_birth = LocalDate.parse(request.date_of_birth),
            nationality = request.nationality,
            salary = request.salary
        )
        kycRepository.save(newProfile)
    }
}

data class ProfileRequest(
    var user_id: Long,
    val date_of_birth: String,
    val nationality: String,
    val salary: Float
)
