package com.tanitama.green.data.model.response.history


import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("data")
    val `data`: List<Data>
)