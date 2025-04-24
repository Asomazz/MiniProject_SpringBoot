package com.example.MiniProject.MiniProject.users

import com.example.MiniProject.MiniProject.accounts.AccountEntity
import com.example.MiniProject.MiniProject.kyc.KycEntity

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val username: String,
    val password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var accounts: List<AccountEntity> = listOf(),

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    val kyc: KycEntity? = null,

    @Enumerated(EnumType.STRING)
    val role: Roles = Roles.USER
) {
    constructor() : this(null, "", "", listOf(), null, Roles.USER)
}

enum class Roles {
    USER, ADMIN
}
