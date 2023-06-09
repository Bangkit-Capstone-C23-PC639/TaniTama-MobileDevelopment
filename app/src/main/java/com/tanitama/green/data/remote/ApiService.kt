package com.tanitama.green.data.remote

import com.tanitama.green.data.model.request.LoginUserRequestBody
import com.tanitama.green.data.model.request.RegisterRequestBody
import com.tanitama.green.data.model.response.detections.DetectionsResponse
import com.tanitama.green.data.model.response.diseasebyid.DiseaseByIdResponse
import com.tanitama.green.data.model.response.history.HistoryResponse
import com.tanitama.green.data.model.response.login.LoginResponse
import com.tanitama.green.data.model.response.register.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {

    @Headers("Accept: application/json")
    @POST("register")
    suspend fun registerUser(
        @Body body: RegisterRequestBody
    ): Response<RegisterResponse>

    @Headers("Accept: application/json")
    @POST("login")
    suspend fun loginUser(
        @Body body: LoginUserRequestBody
    ): Response<LoginResponse>

    @Headers("Accept: application/json")
    @Multipart
    @POST("detections")
    suspend fun detectDisease(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part
    ): Response<DetectionsResponse>

    @Headers("Accept: application/json")
    @GET("history")
    suspend fun getDetectionHistory(
        @Header("Authorization") authorization: String
    ): Response<HistoryResponse>


    @Headers("Accept: application/json")
    @GET("diseases/{id}")
    suspend fun getDiseaseById(
        @Path("id") id: Int
    ): Response<DiseaseByIdResponse>

    @Headers("Accept: application/json")
    @DELETE("detections/{id}")
    suspend fun deleteDetectionById(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<Unit>

}