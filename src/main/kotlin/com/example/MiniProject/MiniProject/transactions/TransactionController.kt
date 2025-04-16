package com.example.MiniProject.MiniProject.transactions

import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping("/accounts/v1/accounts/transfer")
    fun transfer(@RequestBody request: TransferRequest): Map<String, BigDecimal> {
        return transactionService.transfer(request)
    }
}
