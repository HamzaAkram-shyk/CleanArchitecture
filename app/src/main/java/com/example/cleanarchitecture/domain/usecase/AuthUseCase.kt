package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.util.Resource
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {


    suspend fun executeSignUp(name: String, password: String, email: String): Resource<String> {
        return authRepository.createAccount(name, password, email)
    }

    suspend fun userIsLogin() = authRepository.getLoginStatus()


}