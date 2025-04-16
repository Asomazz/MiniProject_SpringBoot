package com.example.MiniProject.MiniProject.transactions

import com.example.MiniProject.MiniProject.accounts.AccountEntity
import jakarta.persistence.*

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "source_account_id")
    val source_account: AccountEntity,

    @ManyToOne
    @JoinColumn(name= "destination_account_id")
    val destination_account: AccountEntity,

    val amount: Float,

    ){
    constructor() : this(null, AccountEntity(), AccountEntity(), 0.0f)
}
