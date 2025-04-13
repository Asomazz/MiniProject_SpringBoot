package com.example.MiniProject.MiniProject.users

import com.example.MiniProject.MiniProject.accounts.AccountEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>{

}

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val username: String,


    val password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var accounts: List<AccountEntity> = listOf()


){
    constructor() : this(null, "","")
}
