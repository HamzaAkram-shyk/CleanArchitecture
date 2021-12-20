package com.example.cleanarchitecture.domain.usecase

import android.provider.ContactsContract
import com.example.cleanarchitecture.domain.repository.AuthRepository
import javax.inject.Inject

class SigInUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun login(email: String, password: String) {

    }

    fun getLoginStatus() = authRepository.getLoginStatus()
}