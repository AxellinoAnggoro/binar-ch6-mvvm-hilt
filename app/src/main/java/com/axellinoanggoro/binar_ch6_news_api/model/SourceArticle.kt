package com.axellinoanggoro.binar_ch6_news_api.model


import com.google.gson.annotations.SerializedName

data class SourceArticle(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)