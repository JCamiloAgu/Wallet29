package com.camilo.wallet29.ui.account_wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.camilo.wallet29.R
import com.camilo.wallet29.factories.RecyclerViewAdapterFactory
import com.camilo.wallet29.models.Account
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account_wallet.view.*

class AccountWalletFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_account_wallet, container, false)

        val items = arrayListOf<Any>(
            Account(0, "Efectivo", 30000, "Efectivo", null),
            Account(1, "Tarjeta davivienda", 500000, "Tarjeta de crédito", null)
        )

        val adapter = RecyclerViewAdapterFactory(
            context!!,
            R.layout.rc_view_item_account_wallet,
            ::setValuesToItemsRecyclerView,
            items
        )
        root.rcViewAccountsWallet.adapter = adapter
        root.rcViewAccountsWallet.layoutManager = LinearLayoutManager(context!!)
        root.rcViewAccountsWallet.setHasFixedSize(true)


        return root
    }

    private fun setValuesToItemsRecyclerView(holder: RecyclerViewAdapterFactory.ViewHolder, items: ArrayList<Any>, position: Int) {
        val elements = holder.account.getViewHolderItemAccount()
        val current = items[position] as Account

        (elements["txtAccountName"] as TextView).text = current.accountName
        (elements["txtAccountBudget"] as TextView).text = "$${current.accountBalance.toString()}"
        (elements["txtAccountType"] as TextView).text = current.accountType
    }

}
