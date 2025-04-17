package com.example.MiniProject.MiniProject.kyc

import java.math.BigDecimal
import java.time.LocalDate

data class ProfileRequest(
    var userId: Long,
    var dateOfBirth: LocalDate,
    var nationality: String,
    var salary: BigDecimal
)

data class KycResponse(
    val userId: Long,
    val dateOfBirth: LocalDate
)
