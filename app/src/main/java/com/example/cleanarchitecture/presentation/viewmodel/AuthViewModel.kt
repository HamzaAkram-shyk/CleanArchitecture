package com.example.cleanarchitecture.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.usecase.AuthUseCase
import com.example.cleanarchitecture.domain.usecase.LoginUseCase
import com.example.cleanarchitecture.presentation.extentions.getMutableResource
import com.example.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUC: AuthUseCase,
    private val loginUC: LoginUseCase
) : BaseViewModel() {
    private var observer = getMutableResource<String>()
    var _observer: LiveData<Resource<String>> = observer
    var response = getMutableResource<APIResponse>()
    fun createAccount(name: String, password: String, email: String) {
        observer.value = Resource.Loading()
        hasInternet(observer) {
            viewModelScope.launch(Dispatchers.IO) {
                observer.postValue(authUC.executeSignUp(name, password, email))
            }
        }
    }

    fun isLogin(): Boolean {
        var isLogin = false
        viewModelScope.launch {
            isLogin = authUC.userIsLogin()
        }
        return isLogin
    }

    fun getData() {
        hasInternet(response) {
            viewModelScope.launch {
                loginUC.executeFlow("sports").collect { resource ->
                    response.value = resource
                }
            }
        }

    }

}