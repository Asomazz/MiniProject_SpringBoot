package com.example.MiniProject.MiniProject.kyc

import com.example.MiniProject.MiniProject.users.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface KycRepository : JpaRepository<KycEntity, Long>{
    fun findByUserId(userId: Long): KycEntity? //we need to define it because user_id is not a direct field in the kyc entity
}

@Entity
@Table(name = "kyc")
data class KycEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var date_of_birth: LocalDate,

    var nationality: String,

    var salary: Float,

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    var user: UserEntity,

    ){
    constructor() : this(null, LocalDate.now(), "", 0.0f, UserEntity())
}
