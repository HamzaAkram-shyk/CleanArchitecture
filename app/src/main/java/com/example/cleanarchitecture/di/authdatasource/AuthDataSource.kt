package com.example.cleanarchitecture.di.authdatasource

import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.util.Resource

interface AuthDataSource {
    suspend fun login(email: String, password: String): Resource<String>
    suspend fun signUp(name: String, email: String, password: String): Resource<UserToken>
}