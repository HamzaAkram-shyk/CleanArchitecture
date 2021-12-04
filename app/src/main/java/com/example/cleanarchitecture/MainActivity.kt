package com.example.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.presentation.viewmodel.AuthViewModel
import com.example.cleanarchitecture.presentation.viewmodel.NewsViewModel
import com.example.cleanarchitecture.util.NetworkConnection
import com.example.cleanarchitecture.util.NetworkStatus
import com.example.cleanarchitecture.util.NetworkStatusHelper

import com.example.cleanarchitecture.util.Resource
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var loader: MaterialCardView
    private val viewModel: NewsViewModel by getViewModels()
   // private val authViewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var networkConnection: NetworkConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loader = findViewById(R.id.loader)
//        Toast.makeText(this, "User login == ${authViewModel.isLogin()}", Toast.LENGTH_SHORT).show()
//        findViewById<Button>(R.id.loginBtn).setOnClickListener {
//            // viewModel.makeLoginRequest("sports")
//            // viewModel.showToast("Hello there.....")
//            //  authViewModel.createAccount("Hamza", "123456", "hamza.sourcecode@gmail.com")
//            authViewModel.getData()
//        }
//        authViewModel.response.observe(this, Observer {
//            when (it) {
//                is Resource.Success -> {
//                    loader.visibility = View.GONE
//                    Toast.makeText(this, "Success = ${it.data}", Toast.LENGTH_SHORT).show()
//                }
//                is Resource.Error -> {
//                    loader.visibility = View.GONE
//                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
//                }
//                is Resource.Loading -> {
//                    loader.visibility = View.VISIBLE
//                }
//            }
//        })
//        networkConnection.observe(this, Observer {
//            Toast.makeText(this, "Internet is Changing...", Toast.LENGTH_SHORT).show()
//        })

//        NetworkStatusHelper(this).observe(this, Observer {
//            when (it) {
//                is NetworkStatus.Available -> {
//                    Toast.makeText(this, "Internet is Connected...", Toast.LENGTH_SHORT).show()
//                }
//
//                is NetworkStatus.Unavailable -> {
//                    Toast.makeText(this, "Internet is Not Connected", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

    }


}