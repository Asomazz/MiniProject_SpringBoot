package com.example.MiniProject.MiniProject.transactions

import java.math.BigDecimal

data class TransferRequest(
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val amount: BigDecimal
)