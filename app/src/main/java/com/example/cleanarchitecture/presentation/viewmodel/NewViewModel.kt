package com.example.cleanarchitecture.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewViewModel(private val useCase: GetNewsHeadlinesUseCase) : ViewModel() {

    private val news: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    val newsObserver = news

    fun getTopHeadlinesNews(page: Int, country: String) {
        news.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            news.value = useCase.execute(page, country)
        }

    }

}