package com.example.MiniProject.MiniProject.accounts


import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long>{
    fun findByAccountNumber(accountNumber: String): AccountEntity?
}
