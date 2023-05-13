package com.axellinoanggoro.binar_ch6_news_api.model


import com.google.gson.annotations.SerializedName

data class ResponseDataSource(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
)