package com.example.cleanarchitecture.presentation.extentions

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.util.Resource
import kotlin.reflect.KClass


fun <T> MutableLiveData<T>.getMutableResource(): MutableLiveData<Resource<T>> {
    return MutableLiveData<Resource<T>>()
}

fun <T> Any.getMutableResource(): MutableLiveData<Resource<T>> {
    return MutableLiveData<Resource<T>>()
}

fun ImageView.setGlideImage(src: Any, context: Context) {
    try {
        Glide.with(context)
            .load(src)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

}



