package com.example.cleanarchitecture.data.repository.authmodule

import com.example.cleanarchitecture.util.Resource

class AuthDataSourceImp : AuthDataSource {

    override suspend fun login(email: String, password: String, coupan: Int): Resource<String> {
        return Resource.Success("$password $coupan")
    }
}