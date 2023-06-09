package com.tanitama.green.data.model.response.history


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("accuracy")
    val accuracy: Double,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("time_predict")
    val timePredict: Double,
    @SerializedName("user_id")
    val userId: UserId
)