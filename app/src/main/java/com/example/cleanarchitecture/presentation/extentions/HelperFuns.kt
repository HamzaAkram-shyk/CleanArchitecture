package com.example.cleanarchitecture.presentation.extentions

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.util.Resource
import com.example.cleanarchitecture.util.SingleLiveEvent
import com.example.cleanarchitecture.validation.FieldType


fun <T> MutableLiveData<T>.getMutableResource(): MutableLiveData<Resource<T>> {
    return MutableLiveData<Resource<T>>()
}

fun <T> Any.getMutableResource(): MutableLiveData<Resource<T>> {
    return MutableLiveData<Resource<T>>()
}

fun <T> MutableLiveData<T>.asSingleEvent(): SingleLiveEvent<T> {

    return SingleLiveEvent<T>()

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

fun EditText.isValidField(): Boolean {
    return if (this.text.toString().trim().isNotEmpty()) {
        true
    } else {
        this.error = "This Field Can't be Null"
        false
    }
}

fun EditText.isValidPassword(): Boolean {
    this.apply {
        return if (!this.isValidField()) {
            false
        } else if (text.toString().length < 8) {
            this.error = "Increase Password Length"
            false
        } else {
            true
        }
    }

}

fun EditText.isValidEmail(): Boolean {
    this.apply {
        return if (!this.isValidField()) {
            false
        } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches())) {
            this.error = "Enter Correct Email Pattern"
            false
        } else {
            return true
        }
    }
}

fun List<FieldType>.safeFieldCall(callback: (ArrayList<String>) -> Unit) {
    val list = ArrayList<String>()
    var counter = 0
    this.forEach { fieldType ->
        counter++
        when (fieldType) {
            is FieldType.BasicField -> {
                if (fieldType.field.isValidField()) {
                    list.add(fieldType.field.text.toString())
                }
            }
            is FieldType.EmailField -> {
                if (fieldType.field.isValidEmail()) {
                    list.add(fieldType.field.text.toString())
                }
            }

            is FieldType.PasswordField -> {
                if (fieldType.field.isValidPassword()) {
                    list.add(fieldType.field.text.toString())
                }
            }
        }
        Log.e("data", "count : $counter")
    }
    callback(list)
}



