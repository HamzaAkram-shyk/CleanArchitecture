package com.example.cleanarchitecture

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.base.BaseActivity
import com.example.cleanarchitecture.base.NetworkCallback

class NewActivity : BaseActivity(), NetworkCallback {
    private lateinit var view: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        view = findViewById(R.id.parent)
        setCallback(this)
    }

    override fun networkChange(change: Boolean) {
        if (change) {
            view.setBackgroundResource(R.color.green)
        } else {
            view.setBackgroundResource(R.color.red)
        }
    }


}