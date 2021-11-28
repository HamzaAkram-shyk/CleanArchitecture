package com.example.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.presentation.viewmodel.NewsViewModel
import com.example.cleanarchitecture.presentation.viewmodelfactory.NewsViewModelFactory
import com.example.cleanarchitecture.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    @Inject
    lateinit var factory: NewsViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        viewModel.getNews(20,"za")
        viewModel._newsLiveData.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    Log.d("data", "Success = ${it.data.toString()}")
                }
                is Resource.Error -> {
                    Log.d("data", "Error = ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d("data", "Loading..........")
                }
            }
        })

    }
}