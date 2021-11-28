package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import javax.inject.Inject

class GetNewsHeadlinesUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun execute(page: Int, country: String): Resource<APIResponse> {
        return repository.getNewsHeadlines(page, country)
    }
}