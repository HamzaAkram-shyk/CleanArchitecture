package com.example.cleanarchitecture

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.base.BaseActivity
import com.example.cleanarchitecture.base.NetworkCallback
import com.example.cleanarchitecture.util.NetworkConnection

class NewActivity : BaseActivity(), NetworkCallback {
    private lateinit var view: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        setConnectionCallback(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        view = findViewById(R.id.parent)
    }


    override fun onInternetDisconnect() {
        view.setBackgroundResource(R.color.red)
        Toast.makeText(this, "Disconnect", Toast.LENGTH_SHORT).show()
    }

    override fun onInternetConnect() {
        view.setBackgroundResource(R.color.green)
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
    }


}