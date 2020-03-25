package com.camilo.wallet29.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camilo.wallet29.factories.ViewHolderFactory

class RecyclerViewAdapter(
    private val ctx: Context,
    private val layoutResource: Int,
    private val mOnBindViewHolder: (holder: ViewHolder, items: List<Any>, position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var  items = arrayListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(layoutResource, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mOnBindViewHolder(holder, items as List<Any>, position)
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val account = ViewHolderFactory(itemView)
    }

    fun <T> setData(mItems: List<T>) {
        items = mItems as ArrayList<Any>
        notifyDataSetChanged()
    }


}