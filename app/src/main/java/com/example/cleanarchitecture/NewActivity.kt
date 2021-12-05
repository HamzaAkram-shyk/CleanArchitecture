package com.example.cleanarchitecture

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.base.BaseActivity
import com.example.cleanarchitecture.base.NetworkCallback
import com.example.cleanarchitecture.presentation.extentions.setGlideImage
import com.example.cleanarchitecture.presentation.viewmodel.AuthViewModel
import com.example.cleanarchitecture.util.NetworkConnection
import com.example.cleanarchitecture.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@AndroidEntryPoint
class NewActivity : BaseActivity(), NetworkCallback {
    private lateinit var view: ConstraintLayout
    private val authViewModel: AuthViewModel by getViewModels()
    private val image1: ImageView by lazy {
        findViewById(R.id.imageView)

    }
    private val image2: ImageView by lazy {
        findViewById(R.id.imageView2)

    }
    private val imagePath =
        "https://external-preview.redd.it/Eoz-M60AS1LzGBUEQgBGUcaN7XXUMknvoMEMlyY3gDQ.jpg?width=640&crop=smart&auto=webp&s=67a52a069b26d835eaa7c5ceda07e4bc06dd474d"

    override fun onCreate(savedInstanceState: Bundle?) {
        setConnectionCallback(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        view = findViewById(R.id.parent)
        authViewModel._observer.observe(this, Observer {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(this, "${it.data}", Toast.LENGTH_SHORT).show()
                }
            }
        })
//        lifecycleScope.launch(Dispatchers.IO) {
//            val bitmap =
//                BitmapFactory.decodeStream(URL(imagePath).openConnection().getInputStream())
//            withContext(Dispatchers.Main) {
//                image2.setGlideImage(bitmap,
//                    applicationContext)
//            }
//        }

        image1.setGlideImage(R.drawable.icon, applicationContext)


    }


    override fun onInternetDisconnect() {
        view.setBackgroundResource(R.color.red)
        Toast.makeText(this, "Disconnect", Toast.LENGTH_SHORT).show()
        // authViewModel.executeNetworkCall()
    }

    override fun onInternetConnect() {
        view.setBackgroundResource(R.color.green)
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
        //authViewModel.executeNetworkCall()
    }


}