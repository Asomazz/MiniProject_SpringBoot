package com.example.MiniProject.MiniProject.kyc

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KycRepository : JpaRepository<KycEntity, Long>{
    fun findByUserId(userId: Long): KycEntity? //we need to define it because user_id is not a direct field in the kyc entity
}


