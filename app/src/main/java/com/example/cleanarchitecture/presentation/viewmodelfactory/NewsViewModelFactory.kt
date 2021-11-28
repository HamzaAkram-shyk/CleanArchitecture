package com.example.cleanarchitecture.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.domain.usecase.GetCategoryNewsUseCase
import com.example.cleanarchitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.cleanarchitecture.presentation.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val useCase: GetNewsHeadlinesUseCase,
    private val categoryUseCase: GetCategoryNewsUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(useCase, categoryUseCase) as T
        } else {
            throw IllegalAccessError("Error Argument")
        }
    }

}