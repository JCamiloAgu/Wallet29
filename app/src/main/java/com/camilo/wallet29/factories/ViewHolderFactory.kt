package com.camilo.wallet29.factories

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.view.*

class ViewHolderFactory(val item: View) {

    fun getViewsItemAccounts(): MutableMap<String, View>{
        val txtAccountName: TextView = item.txtAccountName
        val txtAccountBudget: TextView = item.txtAccountBudge
        val txtAccountType: TextView = item.txtAccountType

        val mutableMap = mutableMapOf<String, View>()
        mutableMap["txtAccountName"] = txtAccountName
        mutableMap["txtAccountBudget"] = txtAccountBudget
        mutableMap["txtAccountType"] = txtAccountType

        return mutableMap
    }

}