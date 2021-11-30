package com.example.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.presentation.viewmodel.NewsViewModel

import com.example.cleanarchitecture.util.Resource
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var loader: MaterialCardView
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
//        viewModel.getNewsCategory("sports")
//        viewModel._newsLiveData.observe(this, Observer {
//            when (it) {
//                is Resource.Success -> {
//                    val result = it.data!!
//                    result.articles.forEach { article ->
//                        Log.d("data", "Title = ${article.title}: Content = ${article.content}")
//                    }
//                }
//                is Resource.Error -> {
//                    Log.d("data", "Error = ${it.message}")
//                }
//                is Resource.Loading -> {
//                    Log.d("data", "Loading..........")
//                }
//            }
//        })
        loader = findViewById(R.id.loader)
        findViewById<Button>(R.id.loginBtn).setOnClickListener {
            viewModel.makeLoginRequest("sports")
        }
        viewModel._tokenLiveData.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    loader.visibility = View.GONE
                    Log.e("data", "usertoken = ${it.data}")
                    Toast.makeText(this, "${it.data}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    loader.visibility = View.GONE
                    Log.e("data", "Error = ${it.message}")
                }
                is Resource.Loading -> {
                    loader.visibility = View.VISIBLE
                }
            }
        })

    }


}