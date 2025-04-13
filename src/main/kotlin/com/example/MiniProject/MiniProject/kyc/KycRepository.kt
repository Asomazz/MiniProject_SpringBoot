package com.example.MiniProject.MiniProject.kyc

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface KycRepository : JpaRepository<KycEntity, Long>{

}

@Entity
@Table(name = "kyc")
data class KycEntity(
    @Id
    @OneToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val dob: Date,

    val nationality: String,

    val salary: Float,

    ){
    constructor() : this(null, Date(), "", 0.0f)
}
