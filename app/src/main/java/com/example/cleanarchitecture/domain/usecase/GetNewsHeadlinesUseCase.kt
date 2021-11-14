package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource

class GetNewsHeadlinesUseCase(private val repository: NewsRepository) {

    suspend fun execute(page: Int, country: String):Resource<APIResponse>{
        return repository.getNewsHeadlines(page,country)
    }
}