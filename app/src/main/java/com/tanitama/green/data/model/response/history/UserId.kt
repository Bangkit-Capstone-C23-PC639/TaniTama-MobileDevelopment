package com.tanitama.green.data.model.response.history


import com.google.gson.annotations.SerializedName

data class UserId(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)