package com.camilo.wallet29.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.camilo.wallet29.R
import com.camilo.wallet29.factories.RecyclerViewAdapterFactory
import com.camilo.wallet29.models.Summary
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_summary.view.*
import java.util.*

class SummaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_summary, container, false)

        val rcView = root.rcViewSummary

        val items = arrayListOf<Any>()

        for (i in 0..20) {
            items.add(
                Summary(
                    0, "Tiempo Libre $i", 8900, 3, 35, "Marzo", 2020
                )
            )
        }


        val adapter = RecyclerViewAdapterFactory(
            context!!,
            R.layout.rc_view_item_summary,
            ::setValuesToItemsRecyclerView,
            items
        )

        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.setHasFixedSize(true)

        val margins = (rcView.layoutParams as ConstraintLayout.LayoutParams).apply {
            bottomMargin = (activity!!.customBottomBar.height)
        }
        rcView.layoutParams = margins

        return root
    }

    private fun setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapterFactory.ViewHolder,
        items: ArrayList<Any>,
        position: Int
    ) {
        val elements = holder.account.getViewHolderItemSummary()

        items.sortByDescending {
            it as Summary
            it.categoryName
        }

        val current = items[position] as Summary

        (elements["txtCategoryName"] as TextView).text = current.categoryName
        (elements["progressBarSummary"] as ProgressBar).progress = current.percentOfTotal
        (elements["txtTotalTransaction"] as TextView).text = "$ ${current.totalTransaction}"
        (elements["txtTransactionPercent"] as TextView).text = "% ${current.percentOfTotal}"

    }
}
