package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<String>
    suspend fun createAccount(name: String, password: String, email: String): Flow<APIResponse>
    fun getLoginStatus(): Boolean
    suspend fun getUserToken(): String?
    suspend fun setLoginStatus(isLogin: Boolean)
    suspend fun saveToken(token: String)
}