package com.example.MiniProject.MiniProject.kyc


import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class KycService(
    private val kycRepository: KycRepository,
    private val userRepository: UserRepository
) {
    fun createProfile(request: ProfileRequest): ProfileRequest {
        var user = userRepository.findById(request.user_id).orElseThrow();
        var existingKyc = kycRepository.findByUserId(request.user_id)

        if (existingKyc != null) {
            existingKyc.date_of_birth = request.date_of_birth
            existingKyc.nationality = request.nationality
            existingKyc.salary = request.salary.toFloat()
            kycRepository.save(existingKyc)
        } else {
            var newKyc = KycEntity(
                user = user,
                date_of_birth = request.date_of_birth,
                nationality = request.nationality,
                salary = request.salary.toFloat()
            )
            kycRepository.save(newKyc)
        }

        return request
    }

    fun getKycByUserId(userId: Long): KycResponse {
        val kyc = kycRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("KYC not found for user ID: $userId") //validation point 3

        return KycResponse(
            userId = kyc.user.id!!,
            date_of_birth = kyc.date_of_birth
        )
    }
}