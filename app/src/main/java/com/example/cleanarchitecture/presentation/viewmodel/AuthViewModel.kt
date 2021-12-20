package com.example.cleanarchitecture.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.usecase.SigInUseCase
import com.example.cleanarchitecture.domain.usecase.SignUpUseCase
import com.example.cleanarchitecture.presentation.extentions.asSingleEvent
import com.example.cleanarchitecture.presentation.extentions.getMutableResource
import com.example.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUC: SignUpUseCase,
    private val sigInUc: SigInUseCase
) : BaseViewModel() {
    private var observer = getMutableResource<APIResponse>().asSingleEvent()
    var _observer: LiveData<Resource<APIResponse>> = observer

    fun createAccount(name: String, password: String, email: String) {
        observer.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            signUpUC.createAccount(name, password, email).collect {
                observer.postValue(it)
            }
        }
    }

    fun isUserLogin() = sigInUc.getLoginStatus()

}