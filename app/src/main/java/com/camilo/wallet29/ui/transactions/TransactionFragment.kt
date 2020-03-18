package com.camilo.wallet29.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.camilo.wallet29.R
import com.camilo.wallet29.factories.RecyclerViewAdapterFactory
import com.camilo.wallet29.models.Transactions
import kotlinx.android.synthetic.main.fragment_transaction.view.*


class TransactionFragment : Fragment() {

    var totalValuesTransactions = 0
    var dateDayNumber = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_transaction, container, false)

        val items = arrayListOf<Any>(
            Transactions(0, "Restaurantes", 7000, "18", "Mi Efectivo"),
            Transactions(0, "Restaurantes", 8900, "18", "Mi Efectivo"),
            Transactions(1, "Coche", 9000, "16", "Tarjeta daviplata"),
            Transactions(0, "Restaurantes", 8900, "18", "Mi Efectivo")
            )

        val adapter = RecyclerViewAdapterFactory(
            context!!,
            R.layout.rc_view_item_transaction,
            ::setValuesToItemsRecyclerView,
            items
        )
        root.rcViewTransactions.adapter = adapter
        root.rcViewTransactions.layoutManager = LinearLayoutManager(context)
        root.rcViewTransactions.setHasFixedSize(true)

        return root
    }

    private fun setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapterFactory.ViewHolder,
        current: Any
    ) {
        val elements = holder.account.getViewHolderItemTransaction()
        current as Transactions

        totalValuesTransactions += current.transactionValue

        //TODO Debo organizar esto para que reconozca el número del día dinámicamente
        if(dateDayNumber !=  current.transactionDate.toInt())
            (elements["layoutDateInformation"] as ConstraintLayout).visibility = View.VISIBLE

        dateDayNumber = current.transactionDate.toInt()

        // Hasta acá

        (elements["txtDayNumber"] as TextView).text = current.transactionDate
        (elements["txtDay"] as TextView).text = "Miércoles"
        (elements["txtMonthAndYear"] as TextView).text = "Marzo 2020"
        (elements["totalValuesTransactions"] as TextView).text = "$$totalValuesTransactions"
        (elements["txtCategoryName"] as TextView).text = current.category
        (elements["txtTransactionValue"] as TextView).text = "$${current.transactionValue.toString()}"
        (elements["txtAccountName"] as TextView).text = current.accountAffect


    }
}
