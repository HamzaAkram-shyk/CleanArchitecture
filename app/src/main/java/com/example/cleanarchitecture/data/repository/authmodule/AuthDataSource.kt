package com.example.cleanarchitecture.data.repository.authmodule

import com.example.cleanarchitecture.util.Resource

interface AuthDataSource {
    suspend fun login(email: String, password: String): Resource<String>
}