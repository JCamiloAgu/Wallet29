package com.camilo.wallet29.factories

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.view.*
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.view.txtAccountName
import kotlinx.android.synthetic.main.rc_view_item_transaction.view.*

class ViewHolderFactory(val item: View) {

    fun getViewHolderItemAccount(): MutableMap<String, View>{
        val txtAccountName: TextView = item.txtAccountName
        val txtAccountBudget: TextView = item.txtAccountBudge
        val txtAccountType: TextView = item.txtAccountType

        val mutableMap = mutableMapOf<String, View>()
        mutableMap["txtAccountName"] = txtAccountName
        mutableMap["txtAccountBudget"] = txtAccountBudget
        mutableMap["txtAccountType"] = txtAccountType

        return mutableMap
    }

    fun getViewHolderItemTransaction(): MutableMap<String, View>{

        //Items Date Information
        val layoutDateInformation: ConstraintLayout = item.dateInformation
        val txtDayNumber: TextView = item.txtDayNumber
        val txtDay: TextView = item.txtDay
        val txtMonthAndYear: TextView = item.txtMonthAndYear
        val txtTotalValuesTransactions: TextView = item.txtTotal

        //Items Transaction Details
        val txtCategoryName: TextView = item.txtCategoryName
        val txtTransactionValue: TextView = item.txtTransactionValue
        val txtAccountName: TextView = item.txtAccountName

        val mutableMap = mutableMapOf<String, View>()
        mutableMap["layoutDateInformation"] = layoutDateInformation
        mutableMap["txtDayNumber"] = txtDayNumber
        mutableMap["txtDay"] = txtDay
        mutableMap["txtMonthAndYear"] = txtMonthAndYear
        mutableMap["totalValuesTransactions"] = txtTotalValuesTransactions
        mutableMap["txtCategoryName"] = txtCategoryName
        mutableMap["txtTransactionValue"] = txtTransactionValue
        mutableMap["txtAccountName"] = txtAccountName

        return mutableMap
    }

}