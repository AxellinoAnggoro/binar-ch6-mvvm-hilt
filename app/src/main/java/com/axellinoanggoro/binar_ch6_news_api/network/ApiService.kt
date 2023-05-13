package com.axellinoanggoro.binar_ch6_news_api.network

import com.axellinoanggoro.binar_ch6_news_api.model.Article
import com.axellinoanggoro.binar_ch6_news_api.model.ResponseDataSource
import com.axellinoanggoro.binar_ch6_news_api.model.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines/sources")
    fun getAllSource(
        @Query("category") category: String,
        @Query("apiKey") apiKey : String = "30c88f2ab641452ab393bef502f9fcc0"
    ) : Call<ResponseDataSource>

    @GET("top-headlines")
    fun getAllArticles(
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String = "30c88f2ab641452ab393bef502f9fcc0"
    ): Call<List<Article>>
}