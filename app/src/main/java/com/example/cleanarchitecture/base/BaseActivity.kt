package com.example.cleanarchitecture.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.util.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
open class BaseActivity() : AppCompatActivity() {

    lateinit var callback: NetworkCallback

    @Inject
    protected lateinit var networkConnection: NetworkConnection
    fun setConnectionCallback(callback: NetworkCallback) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkConnection.observe(this, Observer {
            if (it) {
                callback.onInternetConnect()
            } else {
                callback.onInternetDisconnect()
            }
        })

        ///  Toast.makeText(applicationContext, "Hello there....", Toast.LENGTH_SHORT).show()
    }

}


interface NetworkCallback {
    fun onInternetDisconnect()
    fun onInternetConnect()
}