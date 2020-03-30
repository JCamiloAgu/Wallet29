package com.camilo.wallet29.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camilo.wallet29.factories.ViewHolderFactory
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.view.*

class RecyclerViewAdapter(
    private val ctx: Context,
    private val layoutResource: Int,
    private val mOnBindViewHolder: (holder: ViewHolder, items: List<Any>, position: Int) -> Unit,
    private val onItemClick: (itemPosition: Int) -> Unit = {}
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var items: ArrayList<Any> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(ctx).inflate(layoutResource, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mOnBindViewHolder(holder, items as List<Any>, position)
    }

    fun <T> setData(mItems: List<T>) {
        items = mItems as ArrayList<Any>
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val account = ViewHolderFactory(itemView)

        init {
            item.rcViewItemAccountWalletRoot.setOnClickListener { onItemClick(adapterPosition) }
        }
    }


}