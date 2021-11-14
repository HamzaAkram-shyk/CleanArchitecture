package com.example.cleanarchitecture.data.repository.datasource

import com.example.cleanarchitecture.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(page: Int, country: String): Response<APIResponse>

}