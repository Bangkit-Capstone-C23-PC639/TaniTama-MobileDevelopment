package com.tanitama.green.data.model.response.detections


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("recomendation")
    val recomendation: String,
    @SerializedName("sample_img")
    val sampleImg: String,
    @SerializedName("updated_at")
    val updatedAt: String
) : Parcelable