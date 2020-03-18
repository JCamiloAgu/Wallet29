package com.camilo.wallet29.factories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterFactory(
    private val ctx: Context,
    private val layoutResource: Int,
    private val fn: (holder: ViewHolder, current: Any) -> Unit,
    private val items: ArrayList<Any>
) : RecyclerView.Adapter<RecyclerViewAdapterFactory.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(layoutResource, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = items[position]
        fn(holder, current)
    }


    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val account = ViewHolderFactory(itemView)
    }

}