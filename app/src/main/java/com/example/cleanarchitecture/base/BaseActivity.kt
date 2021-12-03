package com.example.cleanarchitecture.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.util.ConnectionLiveData

open class BaseActivity() : AppCompatActivity() {
    protected lateinit var connectionLiveData: ConnectionLiveData
    lateinit var callback: NetworkCallback
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer {
            callback.networkChange(it)
        })
    }

    @JvmName("setCallback1")
    fun setCallback(callback: NetworkCallback) {
        this.callback = callback
    }
}


interface NetworkCallback {
    fun networkChange(change: Boolean)
}