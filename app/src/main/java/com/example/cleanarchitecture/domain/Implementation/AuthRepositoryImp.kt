package com.example.cleanarchitecture.domain.Implementation

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.repository.authmodule.AuthDataSource
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.data.repository.localdatasource.NewsLocalDataSource
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val dataStore: AuthDataSource
) : AuthRepository {
    override suspend fun login(email: String, password: String): Resource<String> {
        delay(2000)
        return Resource.Success(email)
    }

    override suspend fun createAccount(
        name: String,
        password: String,
        email: String
    ): Flow<APIResponse> {
        return flow {
            emit(newsRemoteDataSource.getCategoryNews("sports").body()!!)
        }
    }


    override  fun getLoginStatus(): Boolean {
        return dataStore.getLoginStatus()
    }

    override suspend fun getUserToken(): String? {
        return dataStore.getUserToken()
    }

    override suspend fun setLoginStatus(isLogin: Boolean) {
        dataStore.saveUserLoginStatus(isLogin)
    }

    override suspend fun saveToken(token: String) {
        dataStore.saveUserToken(token)
    }


}