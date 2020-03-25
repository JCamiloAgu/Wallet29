package com.camilo.wallet29.ui.savings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.camilo.wallet29.R
import com.camilo.wallet29.adapters.RecyclerViewAdapter
import com.camilo.wallet29.models.Saving
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_savings.view.*
import java.util.*

class SavingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_savings, container, false)
        val rcView = root.rcViewSavings

        val items = arrayListOf<Any?>()

        for (i in 0..20) {
            items.add(
                Saving(
                    0,
                    "Mi ahorro $i",
                    20,
                    400,
                    "Ninguna",
                    "19/03/2020",
                    "19/04/2020",
                    Calendar.getInstance()
                )
            )
        }


        val adapter = RecyclerViewAdapter(
            context!!,
            R.layout.rc_view_item_savings,
            ::setValuesToItemsRecyclerView
        )

        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.setHasFixedSize(true)

        val margins = (rcView.layoutParams as LinearLayout.LayoutParams).apply {
            bottomMargin = (activity!!.customBottomBar.height)
        }
        rcView.layoutParams = margins



        return root
    }

    private fun setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapter.ViewHolder,
        items: List<Any>,
        position: Int
    ) {
        val elements = holder.account.getViewHolderItemSaving()

        items.sortedByDescending {
            it as Saving
            it.updatedAt ?: it.createdAt
        }

        val current = items[position] as Saving

        val percent = ((current.saved.toDouble() / current.totalToSave.toDouble()) * 100).toInt()

        (elements["txtSavingName"] as TextView).text = current.name
        (elements["progressBarSaving"] as ProgressBar).progress = percent
        (elements["txtTotalSaving"] as TextView).text =
            "$ ${current.saved} / ${current.totalToSave}"
        (elements["txtSavingPercent"] as TextView).text = "% $percent"

    }

}
