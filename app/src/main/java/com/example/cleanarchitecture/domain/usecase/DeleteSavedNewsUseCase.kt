package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteSavedNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun execute(article: Article) = repository.deleteNews(article)
}