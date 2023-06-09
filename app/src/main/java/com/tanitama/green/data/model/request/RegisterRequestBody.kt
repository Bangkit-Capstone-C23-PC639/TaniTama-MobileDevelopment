package com.tanitama.green.data.model.request

data class RegisterRequestBody(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)
