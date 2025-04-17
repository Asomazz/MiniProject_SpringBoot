package com.example.MiniProject.MiniProject.transactions

import com.example.MiniProject.MiniProject.accounts.AccountEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "source_account_id")
    val sourceAccount: AccountEntity,

    @ManyToOne
    @JoinColumn(name= "destination_account_id")
    val destinationAccount: AccountEntity,

    val amount: BigDecimal = BigDecimal.ZERO,

    ){
    constructor() : this(null, AccountEntity(), AccountEntity(), BigDecimal.ZERO)
}
