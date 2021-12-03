package com.example.cleanarchitecture.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.usecase.GetCategoryNewsUseCase
import com.example.cleanarchitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.cleanarchitecture.domain.usecase.LoginUseCase
import com.example.cleanarchitecture.presentation.extentions.getMutableResource
import com.example.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: GetNewsHeadlinesUseCase,
    private val categoryUseCase: GetCategoryNewsUseCase,
    private var loginUseCase: LoginUseCase,
    private val context: Context
) : ViewModel() {
    private var newsLiveData = MutableLiveData<Resource<APIResponse>>()
    private var tokenLiveData = MutableLiveData<Resource<String>>()
    private var new = MutableLiveData<String>().getMutableResource()
    private var observer = getMutableResource<APIResponse>()
    private var response = getMutableResource<String>()

    val _newsLiveData = newsLiveData
    val _tokenLiveData = tokenLiveData

    init {
        viewModelScope.launch {
            observer.value = categoryUseCase.execute("ksnds")

        }
    }

//    fun getNews(page: Int, country: String) =
//        viewModelScope.launch {
//            _newsLiveData.value = useCase.execute(page, country)
//        }


    fun getNewsCategory(category: String) {
        newsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            newsLiveData.postValue(categoryUseCase.execute(category))
        }
    }

    fun makeLoginRequest(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tokenLiveData.postValue(Resource.Loading())
            delay(1000)
            tokenLiveData.postValue(loginUseCase.execute(category))
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }






}

