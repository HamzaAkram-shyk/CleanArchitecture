package com.example.cleanarchitecture.data.repository.localdatasourceImp

import com.example.cleanarchitecture.data.localDb.TokenDao
import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.data.repository.localdatasource.NewsLocalDataSource
import javax.inject.Inject

class NewsLocalDataSourceImp @Inject constructor(
    private val tokenDao: TokenDao
) : NewsLocalDataSource {
    override suspend fun insertArticle(article: Article) {

    }

    override suspend fun registeredUserToken(userToken: UserToken): Long {
        return tokenDao.registeredUserToken(userToken)
    }
}