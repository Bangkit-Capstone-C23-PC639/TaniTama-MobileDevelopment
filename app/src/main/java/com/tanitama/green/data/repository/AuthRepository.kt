package com.tanitama.green.data.repository

import com.tanitama.green.data.model.request.LoginUserRequestBody
import com.tanitama.green.data.model.request.RegisterRequestBody
import com.tanitama.green.data.model.response.login.LoginResponse
import com.tanitama.green.data.model.response.register.RegisterResponse
import com.tanitama.green.data.remote.ApiService
import retrofit2.Response

class AuthRepository(
    private val apiService: ApiService,
) {
    suspend fun registerUser(registerRequestBody: RegisterRequestBody) : Response<RegisterResponse> {
        return apiService.registerUser(registerRequestBody)
    }

    suspend fun loginUser(loginUserRequestBody: LoginUserRequestBody) : Response<LoginResponse> {
        return apiService.loginUser(loginUserRequestBody)
    }

}