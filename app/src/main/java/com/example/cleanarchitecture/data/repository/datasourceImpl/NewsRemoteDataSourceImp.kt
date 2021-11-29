package com.example.cleanarchitecture.data.repository.datasourceImpl

import com.example.cleanarchitecture.data.api.NewsAPIService
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.di.NewsApiService
import com.example.cleanarchitecture.di.NewsApiServiceWithJson
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class NewsRemoteDataSourceImp @Inject constructor(
    @NewsApiService
    private val newsAPIService: NewsAPIService,
    @NewsApiServiceWithJson
    private val news: NewsAPIService
) : NewsRemoteDataSource {

    override suspend fun getTopHeadlines(page: Int, country: String): Response<APIResponse> {
        return newsAPIService.getHeadlinesNews(country, page)
    }

    override suspend fun getCategoryNews(category: String): Response<APIResponse> {
        return newsAPIService.getCategoryNews(category = category)
    }
}