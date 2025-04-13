package com.example.MiniProject.MiniProject.transactions

import com.example.MiniProject.MiniProject.accounts.AccountEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionEntity, Long>{

}

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "account_id")
    val source_account: AccountEntity,

    @ManyToOne
    @JoinColumn(name= "account_id")
    val destination_account: AccountEntity,


    val amount: Float,


    ){
    constructor() : this(null, AccountEntity(), AccountEntity(), 0.0f)
}
