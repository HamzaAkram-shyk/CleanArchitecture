package com.example.cleanarchitecture.data.repository.datasourceImpl

import com.example.cleanarchitecture.data.api.NewsAPIService
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImp @Inject constructor(
    private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource {

    override suspend fun getTopHeadlines(page: Int, country: String): Response<APIResponse> {
        return newsAPIService.getHeadlinesNews(country, page)
    }

    override suspend fun getCategoryNews(category: String): Response<APIResponse> {
        return newsAPIService.getCategoryNews(category = category)
    }
}