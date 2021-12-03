package com.example.cleanarchitecture.presentation.extentions

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.util.Resource
import kotlin.reflect.KClass


fun <T> MutableLiveData<T>.getMutableResource(): MutableLiveData<Resource<T>> {
    return MutableLiveData<Resource<T>>()
}

fun <T> Any.getMutableResource(): MutableLiveData<Resource<T>> {
    return MutableLiveData<Resource<T>>()
}



