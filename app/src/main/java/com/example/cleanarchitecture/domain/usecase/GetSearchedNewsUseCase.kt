package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource

class GetSearchedNewsUseCase(private val repository: NewsRepository) {
    suspend fun execute(query: String): Resource<APIResponse> {
        return repository.getSearchedNews(query)
    }
}