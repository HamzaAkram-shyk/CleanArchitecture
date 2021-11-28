package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.repository.NewsRepository
import javax.inject.Inject

class GetCategoryNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun execute(category: String) = repository.getCategoryNews(category)

}