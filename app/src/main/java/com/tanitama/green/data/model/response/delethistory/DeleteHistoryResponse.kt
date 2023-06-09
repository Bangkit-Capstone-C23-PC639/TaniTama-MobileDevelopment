package com.tanitama.green.data.model.response.delethistory


import com.google.gson.annotations.SerializedName

data class DeleteHistoryResponse(
    @SerializedName("message")
    val message: String
)