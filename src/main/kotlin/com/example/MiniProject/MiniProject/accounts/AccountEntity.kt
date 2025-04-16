package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.transactions.TransactionEntity
import com.example.MiniProject.MiniProject.users.UserEntity
import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "accounts")
data class AccountEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "user_id")
    var user: UserEntity,

    var balance: BigDecimal = BigDecimal.ZERO,

    var is_active: Boolean = true,

    @Column(name = "account_number")
    var accountNumber: String,

    @OneToMany(mappedBy = "source_account", cascade = [CascadeType.ALL])
    var sentTransactions: List<TransactionEntity> = listOf(),

    @OneToMany(mappedBy = "destination_account", cascade = [CascadeType.ALL])
    var receivedTransactions: List<TransactionEntity> = listOf()

){
    constructor() : this(null, UserEntity(), BigDecimal.ZERO,true, "", listOf(), listOf())
}
