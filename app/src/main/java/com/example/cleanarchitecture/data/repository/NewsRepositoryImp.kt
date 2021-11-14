package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImp(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {

    override suspend fun getNewsHeadlines(page: Int, country: String): Resource<APIResponse> {
        return try {
            val response = newsRemoteDataSource.getTopHeadlines(page,country)
            Resource.Success(response.body()!!)
        } catch (e: Exception) {
            Resource.Error("${e.message.toString()}")
        }
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun savedNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}