package com.example.cleanarchitecture.data.api


import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getHeadlinesNews(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = Constant.api_key,
    ): Response<APIResponse>

    @GET("v2/top-headlines")
    suspend fun getCategoryNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Constant.api_key,
    ): Response<APIResponse>


}