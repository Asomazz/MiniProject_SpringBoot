package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.transactions.TransactionEntity
import com.example.MiniProject.MiniProject.users.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long>{
    fun findByAccountNumber(accountNumber: String): AccountEntity?
}

@Entity
@Table(name = "accounts")
data class AccountEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "user_id")
    var user: UserEntity,

    var balance: Float = 0.0f,

    var is_active: Boolean = true,

    @Column(name = "account_number")
    var accountNumber: String,

    @OneToMany(mappedBy = "source_account", cascade = [CascadeType.ALL])
    var sentTransactions: List<TransactionEntity> = listOf(),

    @OneToMany(mappedBy = "destination_account", cascade = [CascadeType.ALL])
    var receivedTransactions: List<TransactionEntity> = listOf()

){
    constructor() : this(null, UserEntity(),0.0f,true, "", listOf(), listOf())
}
