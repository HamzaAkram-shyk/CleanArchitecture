package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(article: Article) = repository.deleteNews(article)
}