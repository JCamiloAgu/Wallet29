package com.camilo.wallet29.interfaces

import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.camilo.wallet29.adapters.RecyclerViewAdapter

abstract class FragmentsView<T>: Fragment() {

    abstract val viewModel: AndroidViewModel
    lateinit var items: LiveData<List<T?>>

    abstract fun setUpRecyclerView(rcView: RecyclerView)
    abstract fun setUpObservers()
    abstract fun fabClick()

    abstract fun setRecyclerViewItemClickListener()

    abstract fun setUpDependencies()

    abstract fun <T> setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapter.ViewHolder,
        items: List<T>,
        position: Int
    )
}