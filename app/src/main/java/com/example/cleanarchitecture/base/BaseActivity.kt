package com.example.cleanarchitecture.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.util.*
import dagger.hilt.android.AndroidEntryPoint


open class BaseActivity() : AppCompatActivity() {
    protected lateinit var connection: NetworkStatusHelper
    lateinit var callback: NetworkCallback
    fun setConnectionCallback(callback: NetworkCallback) {
        this.callback = callback
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connection= NetworkStatusHelper(applicationContext)
        connection.observe(this, Observer {
            when (it) {
                is NetworkStatus.Available -> {
                    callback.onInternetConnect()
                   // Toast.makeText(context, "Available", Toast.LENGTH_SHORT).show()
                }
                is NetworkStatus.Unavailable -> {
                    callback.onInternetDisconnect()
                    //Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT).show()
                }
            }
        })
        Toast.makeText(applicationContext, "Hello there....", Toast.LENGTH_SHORT).show()
    }

}


interface NetworkCallback {
    fun onInternetDisconnect()
    fun onInternetConnect()
}