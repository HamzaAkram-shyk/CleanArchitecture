package com.example.cleanarchitecture.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.usecase.GetCategoryNewsUseCase
import com.example.cleanarchitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(
    private val useCase: GetNewsHeadlinesUseCase,
    private val categoryUseCase: GetCategoryNewsUseCase,
) : ViewModel() {

    private var newsLiveData = MutableLiveData<Resource<APIResponse>>()

    val _newsLiveData = newsLiveData


    fun getNews(page: Int, country: String) =
        viewModelScope.launch {
            _newsLiveData.value = useCase.execute(page, country)
        }


    fun getNewsCategory(category: String) {
        newsLiveData.value = Resource.Loading()
        try {
            viewModelScope.launch(Dispatchers.IO) {
                newsLiveData.postValue(categoryUseCase.execute(category))
            }
        } catch (e: Exception) {
            newsLiveData.value = Resource.Error(e.message.toString())
        }

    }

}