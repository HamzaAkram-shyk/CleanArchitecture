package com.example.extentions.adapater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.presentation.extentions.asSingleEvent
import kotlin.properties.Delegates

abstract class BaseAdapter<T, V : ViewDataBinding>(
    @LayoutRes var layout: Int
) :
    RecyclerView.Adapter<BaseViewHolder<V>>() {

    private var items: ArrayList<T> by Delegates.observable(ArrayList()) { _, _oldValue, _newValue ->
        notifyDataSetChanged()
    }

    val onPress = MutableLiveData<T>()


    fun addItems(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        this.items.add(item)
    }

    fun clearList() {
        this.items.clear()
    }

    fun removeItem(item: T) {
        this.items.remove(item)
    }

    fun getList() = items

    open fun onClick(item: T) {
        Log.e("click item", "$item")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = BaseViewHolder(
        DataBindingUtil.inflate<V>(
            LayoutInflater.from(parent.context),
            layout, parent, false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        onItemInflated(items[position], position, holder.binding)

        holder.binding.root.setOnClickListener {
            Log.e("click item", "${items[position]}")
            onClick(items[position])
            onPress.value = items[position]
        }
        holder.binding.executePendingBindings()
    }

    abstract fun onItemInflated(items: T, position: Int, binding: V)


}