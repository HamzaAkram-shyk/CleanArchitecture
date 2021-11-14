package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(page: Int, country: String): Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse>
    suspend fun savedNews(article: Article)
    suspend fun deleteNews(article: Article)
    suspend fun getSavedNews(): Flow<List<Article>>
}