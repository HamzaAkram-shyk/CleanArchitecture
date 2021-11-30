package com.example.cleanarchitecture.domain.usecase

import android.util.Log
import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun execute(category: String): Resource<String> {
        return try {
            val response = repository.getCategoryNews(category)
            val userToken = UserToken(
                userId = 1,
                userToken = "12h232jny1gyw28721dw23dds323"
            )
            return if (response is Resource.Success) {
                repository.registerUserToken(
                    userToken
                )
                response.data.let {
                    it!!.articles.forEach { article ->
                        Log.e("data", "Title = ${article.title}")
                    }
                }
                Resource.Success(userToken.userToken)
            } else {
                Resource.Error(response.message.toString())
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage.toString())
        }

    }


}