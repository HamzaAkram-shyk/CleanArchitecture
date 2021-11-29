package com.example.cleanarchitecture.data.repository.localdatasourceImp

import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.data.repository.localdatasource.NewsLocalDataSource
import javax.inject.Inject

class NewsLocalDataSourceImp @Inject constructor() : NewsLocalDataSource {
    override suspend fun insertArticle(article: Article) {

    }
}