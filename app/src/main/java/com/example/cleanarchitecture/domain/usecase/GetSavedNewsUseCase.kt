package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(): Flow<List<Article>>{
        return repository.getSavedNews()
    }
}