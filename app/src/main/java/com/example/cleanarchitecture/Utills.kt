package com.example.cleanarchitecture

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

@MainThread
internal inline fun <reified V : ViewModel> ComponentActivity.getViewModels(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<V> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(V::class, { viewModelStore }, factoryPromise)
}
