package com.tanitama.green.data.model.response.login

data class LoginResponse(
    val errors: Errors,
    val message: String,
    val token: String
)