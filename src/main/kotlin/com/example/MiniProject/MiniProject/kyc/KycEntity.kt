package com.example.MiniProject.MiniProject.kyc

import com.example.MiniProject.MiniProject.users.UserEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "kyc")
data class KycEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var dateOfBirth: LocalDate,

    var nationality: String,

    var salary: Float,

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    var user: UserEntity,

    ){
    constructor() : this(null, LocalDate.now(), "", 0.0f, UserEntity())
}