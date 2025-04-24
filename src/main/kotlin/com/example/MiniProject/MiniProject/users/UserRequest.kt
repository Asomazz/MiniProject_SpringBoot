package com.example.MiniProject.MiniProject.users

data class AuthenticationRequest(
    val username: String,
    val password: String,
)

data class AuthenticationResponse(
    val token: String
)