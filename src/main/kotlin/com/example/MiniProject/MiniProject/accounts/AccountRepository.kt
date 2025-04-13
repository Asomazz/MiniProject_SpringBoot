package com.example.MiniProject.MiniProject.accounts

import com.example.MiniProject.MiniProject.transactions.TransactionEntity
import com.example.MiniProject.MiniProject.users.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long>{

}

@Entity
@Table(name = "accounts")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "user_id")
    val user: UserEntity,

    val balance: Float,

    val is_active: Boolean,

    val account_number: Int,

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL])
    var transactions: List<TransactionEntity> = listOf()

){
    constructor() : this(null, UserEntity(), 0.0f,true, 0, listOf())
}
