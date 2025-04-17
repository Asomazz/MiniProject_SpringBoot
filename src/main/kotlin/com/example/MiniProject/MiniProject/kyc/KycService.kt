package com.example.MiniProject.MiniProject.kyc


import com.example.MiniProject.MiniProject.users.UserRepository
import org.springframework.stereotype.Service

@Service
class KycService(
    private val kycRepository: KycRepository,
    private val userRepository: UserRepository
) {
    fun createProfile(request: ProfileRequest): ProfileRequest {
        var user = userRepository.findById(request.userId).orElseThrow();
        var existingKyc = kycRepository.findByUserId(request.userId)

        if (existingKyc != null) {
            existingKyc.dateOfBirth = request.dateOfBirth
            existingKyc.nationality = request.nationality
            existingKyc.salary = request.salary.toFloat()
            kycRepository.save(existingKyc)
        } else {
            var newKyc = KycEntity(
                user = user,
                dateOfBirth = request.dateOfBirth,
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
            dateOfBirth = kyc.dateOfBirth
        )
    }
}