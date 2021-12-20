package com.example.cleanarchitecture.data.repository.authmodule

import com.example.cleanarchitecture.util.Resource

interface AuthDataSource {
    suspend fun saveUserToken(token: String)
    suspend fun saveUserLoginStatus(isLogin: Boolean)
    fun getLoginStatus(): Boolean
    suspend fun getUserToken(): String?

}