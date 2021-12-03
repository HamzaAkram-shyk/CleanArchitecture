package com.example.cleanarchitecture.domain.Implementation

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.repository.authmodule.AuthDataSource
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.data.repository.localdatasource.NewsLocalDataSource
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.util.Resource
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val dataStore: AuthDataSource
) : AuthRepository {
    override suspend fun login(email: String, password: String): Resource<String> {
        return Resource.Success(email)
    }

    override suspend fun createAccount(
        name: String,
        password: String,
        email: String
    ): Resource<String> {
        return try {
            val response = newsRemoteDataSource.getCategoryNews("sports")
            if (response.isSuccessful) {
                response.let {
                    val token = it.body()!!.articles[0].title
                    dataStore.saveUserToken(token)
                    setLoginStatus(true)
                    Resource.Success(token)
                }
            } else {
                Resource.Error(response.message().toString())
            }

        } catch (e: Exception) {
            Resource.Error("${e.localizedMessage.toString()}")
        }
    }

    override suspend fun getLoginStatus(): Boolean {
        return dataStore.getLoginStatus()
    }

    override suspend fun getUserToken(): String? {
        return dataStore.getUserToken()
    }

    override suspend fun setLoginStatus(isLogin: Boolean) {
       dataStore.saveUserLoginStatus(isLogin)
    }


}