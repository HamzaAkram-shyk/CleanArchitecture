package com.example.cleanarchitecture

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.base.BaseActivity
import com.example.cleanarchitecture.base.NetworkCallback
import com.example.cleanarchitecture.databinding.ActivityNewBinding
import com.example.cleanarchitecture.presentation.extentions.safeFieldCall
import com.example.cleanarchitecture.presentation.extentions.setGlideImage
import com.example.cleanarchitecture.presentation.viewmodel.AuthViewModel
import com.example.cleanarchitecture.util.NetworkConnection
import com.example.cleanarchitecture.util.Resource
import com.example.cleanarchitecture.validation.FieldType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@AndroidEntryPoint
class NewActivity : BaseActivity(), NetworkCallback {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivityNewBinding
    private val imagePath =
        "https://external-preview.redd.it/Eoz-M60AS1LzGBUEQgBGUcaN7XXUMknvoMEMlyY3gDQ.jpg?width=640&crop=smart&auto=webp&s=67a52a069b26d835eaa7c5ceda07e4bc06dd474d"
    private lateinit var listOfFields: List<FieldType>
    override fun onCreate(savedInstanceState: Bundle?) {
        setConnectionCallback(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new)
        setFields()
        binding.btn.setOnClickListener {
            listOfFields.safeFieldCall { list ->
                if (list.size == 3) {
                    authViewModel.createAccount(list[0], list[1], list[2])
                }
            }
        }
        authViewModel._observer.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(this, "Success = ${it.data.toString()}", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Error = ${it.message}", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {

                }
            }
        })

//        openCamera { uri ->
//            binding.imageView.setGlideImage(uri!!, applicationContext)
//        }
//
//        openGallery { uri ->
//            binding.imageView.setGlideImage(uri!!, applicationContext)
//        }


    }


    override fun onInternetDisconnect() {
        binding.parent.setBackgroundResource(R.color.red)
    }

    override fun onInternetConnect() {
        binding.parent.setBackgroundResource(R.color.green)
    }

    private fun setFields() {
        listOfFields = listOf(
            FieldType.BasicField(binding.nameField),
            FieldType.PasswordField(binding.passwordField),
            FieldType.EmailField(binding.emailField),
        )
    }

}