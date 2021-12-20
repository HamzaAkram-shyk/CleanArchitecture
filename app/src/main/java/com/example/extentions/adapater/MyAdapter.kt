package com.example.extentions.adapater

import android.util.Log
import android.widget.Toast
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityValidationBinding
import com.example.cleanarchitecture.databinding.ItemLayoutBinding

class MyAdapter(private val itemClick: (String) -> Unit) :
    BaseAdapter<String, ItemLayoutBinding>(R.layout.item_layout) {


    override fun onClick(item: String) {
        super.onClick(item)
        Log.e("click item", item)


    }

    override fun onItemInflated(items: String, position: Int, binding: ItemLayoutBinding) {
        binding.item = items
        binding.itemClick = itemClick
//        binding.image.setOnClickListener {
//
//            itemClick.invoke(items)
//        }

    }


}