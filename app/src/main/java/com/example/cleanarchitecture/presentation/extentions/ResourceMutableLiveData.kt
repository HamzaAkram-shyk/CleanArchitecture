package com.example.cleanarchitecture.presentation.extentions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.util.Resource

class ResourceMutableLiveData<T> : MutableLiveData<Resource<T>>() {


}