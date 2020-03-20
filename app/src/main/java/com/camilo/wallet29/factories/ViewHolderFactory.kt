package com.camilo.wallet29.factories

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.view.*
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.view.txtAccountName
import kotlinx.android.synthetic.main.rc_view_item_savings.view.*
import kotlinx.android.synthetic.main.rc_view_item_summary.view.*
import kotlinx.android.synthetic.main.rc_view_item_transaction.view.*
import kotlinx.android.synthetic.main.rc_view_item_transaction.view.txtCategoryName

class ViewHolderFactory(val item: View) {

    fun getViewHolderItemAccount(): MutableMap<String, View> {
        val txtAccountName: TextView = item.txtAccountName
        val txtAccountBudget: TextView = item.txtAccountBudge
        val txtAccountType: TextView = item.txtAccountType

        val mutableMap = mutableMapOf<String, View>()
        mutableMap["txtAccountName"] = txtAccountName
        mutableMap["txtAccountBudget"] = txtAccountBudget
        mutableMap["txtAccountType"] = txtAccountType

        return mutableMap
    }

    fun getViewHolderItemTransaction(): MutableMap<String, View> {

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

    fun getViewHolderItemSummary(): MutableMap<String, View> {
        val imgViewSummaryItemIcon = item.imgViewSummaryItemIcon
        val txtCategoryName = item.txtCategoryName
        val txtTotalTransaction = item.txtTotalTransaction
        val progressBarSummary = item.progressBarSummary
        val txtTransactionPercent = item.txtTransactionPercent

        val mutableMap = mutableMapOf<String, View>()

        mutableMap["imgViewSummaryItemIcon"] = imgViewSummaryItemIcon
        mutableMap["txtCategoryName"] = txtCategoryName
        mutableMap["progressBarSummary"] = progressBarSummary
        mutableMap["txtTotalTransaction"] = txtTotalTransaction
        mutableMap["txtTransactionPercent"] = txtTransactionPercent

        return mutableMap
    }

    fun getViewHolderItemSaving(): MutableMap<String, View> {
        val imgViewSavingItemIcon = item.imgViewSavingItemIcon
        val txtSavingName = item.txtSavingName
        val txtTotalSaving = item.txtTotalSaving
        val progressBarSaving = item.progressBarSaving
        val txtSavingPercent = item.txtSavingPercent

        val mutableMap = mutableMapOf<String, View>()

        mutableMap["imgViewSavingItemIcon"] = imgViewSavingItemIcon
        mutableMap["txtSavingName"] = txtSavingName
        mutableMap["progressBarSaving"] = progressBarSaving
        mutableMap["txtTotalSaving"] = txtTotalSaving
        mutableMap["txtSavingPercent"] = txtSavingPercent

        return mutableMap
    }


}