package com.tanitama.green.data.model.response.detections


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetectionsResponse(
    @SerializedName("data")
    val data: Data
) : Parcelable