package com.camilo.wallet29.ui.transactions

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.camilo.wallet29.R
import com.camilo.wallet29.factories.RecyclerViewAdapterFactory
import com.camilo.wallet29.models.Transactions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_transaction.view.*
import java.text.SimpleDateFormat
import java.util.*


class TransactionFragment : Fragment() {

    var totalValuesTransactions = 0
    var dateDayNumber = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_transaction, container, false)

        setUpRecyclerView(root)

        return root
    }

    private fun setUpRecyclerView(root: View){
        val items = arrayListOf<Any>(
            Transactions(
                0, "Restaurantes", 8900, Calendar.getInstance(), "Mi Efectivo"
            ),
            Transactions(
                0, "Restaurantes", 7000, Calendar.getInstance(), "Mi Efectivo"
            ),
            Transactions(
                0, "Restaurantes", 8900, Calendar.getInstance(), "Mi Efectivo"
            ),

            Transactions(
                0, "Restaurantes", 8900, Calendar.getInstance(), "Mi Efectivo"
            ), Transactions(
                0, "Restaurantes", 8900, Calendar.getInstance(), "Mi Efectivo"
            )
        )

        val adapter = RecyclerViewAdapterFactory(
            context!!,
            R.layout.rc_view_item_transaction,
            ::setValuesToItemsRecyclerView,
            items
        )
        val rcView = root.rcViewTransactions
        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.setHasFixedSize(true)

        val margins = (rcView.layoutParams as RelativeLayout.LayoutParams).apply {
            bottomMargin = (activity!!.customBottomBar.height)
        }
        rcView.layoutParams = margins
    }

    @SuppressLint("SetTextI18n")
    private fun setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapterFactory.ViewHolder,
        items: ArrayList<Any>,
        position: Int
    ) {

        val elements = holder.account.getViewHolderItemTransaction()

        items.sortByDescending {
            it as Transactions
            it.transactionDate[Calendar.DAY_OF_MONTH]
        }

        val current = items[position] as Transactions

        totalValuesTransactions = 0

        val currentDate = current.transactionDate

        if (dateDayNumber != currentDate[Calendar.DAY_OF_MONTH]) {
            (elements["layoutDateInformation"] as ConstraintLayout).visibility = View.VISIBLE
            items.forEach {
                it as Transactions
                if (currentDate[Calendar.DAY_OF_MONTH] == it.transactionDate[Calendar.DAY_OF_MONTH])
                    totalValuesTransactions += it.transactionValue
            }
            (elements["txtTransactionValue"] as TextView).text =
                "$${current.transactionValue}"
        }

        dateDayNumber = currentDate[Calendar.DAY_OF_MONTH]


        (elements["txtDayNumber"] as TextView).text = currentDate[Calendar.DAY_OF_MONTH].toString()
        (elements["txtDay"] as TextView).text = getStringDayOfWeek(currentDate)
        (elements["txtMonthAndYear"] as TextView).text =
            "${getStringMonth(currentDate)} ${currentDate[Calendar.YEAR]}"
        (elements["totalValuesTransactions"] as TextView).text = "$$totalValuesTransactions"
        (elements["txtCategoryName"] as TextView).text = current.category
        (elements["txtAccountName"] as TextView).text = current.accountAffect
    }

    @SuppressLint("SimpleDateFormat")
    private fun getStringMonth(currentDate: Calendar): String {
        val monthFormat = SimpleDateFormat("MMMM")
        return monthFormat.format(currentDate.time)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getStringDayOfWeek(currentDate: Calendar): String {
        val monthFormat = SimpleDateFormat("EEEE")
        return monthFormat.format(currentDate.time)
    }
}
