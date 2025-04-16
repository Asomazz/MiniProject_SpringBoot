package com.example.MiniProject.MiniProject.accounts

import java.math.BigDecimal

data class AccountRequest(
    var userId: Long,
    var accountNumber: String,
    var initialBalance: BigDecimal,
)

data class AccountResponse(
    var userId: Long,
    var accountNumber: String,
    var initialBalance: BigDecimal
)

data class AccountDTO(
    val id: Long?,
    val accountNumber: String,
    val balance: Float,
    val is_active: Boolean
)