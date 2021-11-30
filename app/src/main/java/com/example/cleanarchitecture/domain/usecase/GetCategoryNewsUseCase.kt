package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import javax.inject.Inject

class GetCategoryNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun execute(category: String) = repository.getCategoryNews(category)



}