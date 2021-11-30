package com.example.cleanarchitecture.data.repository.localdatasource

import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.data.model.Article

interface NewsLocalDataSource {

    suspend fun insertArticle(article: Article)
    suspend fun registeredUserToken(userToken: UserToken):Long
}