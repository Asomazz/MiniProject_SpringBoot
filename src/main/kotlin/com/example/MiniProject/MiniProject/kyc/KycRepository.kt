package com.example.MiniProject.MiniProject.kyc

import com.example.MiniProject.MiniProject.users.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface KycRepository : JpaRepository<KycEntity, Long>{

}

@Entity
@Table(name = "kyc")
data class KycEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val date_of_birth: LocalDate,

    val nationality: String,

    val salary: Float,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    ){
    constructor() : this(null, LocalDate.now(), "", 0.0f, UserEntity())
}
