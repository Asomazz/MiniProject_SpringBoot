package com.example.MiniProject.MiniProject.kyc

import java.math.BigDecimal
import java.time.LocalDate

data class ProfileRequest(
    var user_id: Long,
    var date_of_birth: LocalDate,
    var nationality: String,
    var salary: BigDecimal
)

data class KycResponse(
    val userId: Long,
    val date_of_birth: LocalDate
)
