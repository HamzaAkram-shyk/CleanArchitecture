package com.example.cleanarchitecture.di.authdatasource

import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.util.Resource

class AuthDataSourceImp :AuthDataSource {
    override suspend fun login(email: String, password: String): Resource<String> {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Resource<UserToken> {
        TODO("Not yet implemented")
    }
}