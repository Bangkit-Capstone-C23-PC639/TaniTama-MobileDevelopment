package com.tanitama.green.data.repository

import android.content.Context
import com.tanitama.green.TanitamaApp
import com.tanitama.green.data.model.response.detections.DetectionsResponse
import com.tanitama.green.data.model.response.diseasebyid.DiseaseByIdResponse
import com.tanitama.green.data.model.response.history.HistoryResponse
import com.tanitama.green.data.remote.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File

class DetectionsRepository(
    private val apiService: ApiService
) {
    private val sharedPreferences =
        TanitamaApp.context.getSharedPreferences("credentials", Context.MODE_PRIVATE)

    private fun getBearerToken(): String {
        return sharedPreferences?.getString("bearerToken", "") ?: ""
    }

    private val token = "Bearer ${getBearerToken()}"

    suspend fun detectDisease(file: File): Response<DetectionsResponse> {
        val photoRequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val photoPart = MultipartBody.Part.createFormData("file", file.name, photoRequestBody)

        return apiService.detectDisease(token, photoPart)
    }

    suspend fun getHistory() : Response<HistoryResponse> {
        return apiService.getDetectionHistory(token)
    }

    suspend fun getDiseaseById(id: Int) : Response<DiseaseByIdResponse> {
        return apiService.getDiseaseById(id)
    }

    suspend fun deletDiseaseById(id: Int) : Response<Unit> {
        return apiService.deleteDetectionById(id, token)
    }

}