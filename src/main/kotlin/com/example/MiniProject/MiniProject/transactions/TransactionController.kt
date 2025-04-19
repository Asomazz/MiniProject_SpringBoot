package com.example.MiniProject.MiniProject.transactions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping("/accounts/v1/accounts/transfer")
    fun transfer(@RequestBody request: TransferRequest): ResponseEntity<Any> {
        return try {
            val response = transactionService.transfer(request)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}
