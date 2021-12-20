package com.example.extentions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityNewBinding
import com.example.cleanarchitecture.databinding.ActivityViewBinding
import com.example.extentions.adapater.MyAdapter

class ViewActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    //
    private val rAdapter: MyAdapter by lazy {
        MyAdapter(::catchClick)
    }

    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view)

//        binding.name = R.drawable.cake

        val list = listOf("Hello", "there", "yup", "good")
        rAdapter.addItems(list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rAdapter
        }

    }


    private fun catchClick(data: String) {
        Toast.makeText(this, "$data", Toast.LENGTH_SHORT).show()
    }

}