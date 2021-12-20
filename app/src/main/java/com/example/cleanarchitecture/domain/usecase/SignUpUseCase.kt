package com.example.cleanarchitecture.domain.usecase

import android.provider.ContactsContract
import android.util.Log
import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {


    suspend fun createAccount(
        name: String,
        email: String,
        password: String
    ): Flow<Resource<APIResponse>> {
        return flow {
            authRepository.createAccount(name, email, password).catch { e ->
                emit(Resource.Error(e.localizedMessage.toString()))
            }.collect { response ->
                val token = response.articles[0].title
                authRepository.saveToken(token)
                authRepository.setLoginStatus(true)
                emit(Resource.Success(response))
            }
        }
    }

}