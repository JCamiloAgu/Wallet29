package com.camilo.wallet29.ui.account_wallet

import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camilo.wallet29.R
import com.camilo.wallet29.adapters.RecyclerViewAdapter
import com.camilo.wallet29.factories.FullScreenDialogFactory
import com.camilo.wallet29.interfaces.FragmentsView
import com.camilo.wallet29.local_data.Wallet29RoomDatabase
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.repository.AccountWalletRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account_wallet.view.*
import kotlinx.android.synthetic.main.rc_view_item_account_wallet.*

class AccountWalletFragment : FragmentsView<AccountWalletEntity>() {

    override val viewModel: AccountWalletViewModel by viewModels()

    lateinit var adapter: RecyclerViewAdapter

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_account_wallet, container, false)

        setUpDependencies()

        items = viewModel.data

        setUpRecyclerView(root.rcViewAccountsWallet)
        setUpObservers()
        fabClick()
        return root
    }

    private fun setTotalBudget(it: List<AccountWalletEntity?>) {
        var totalBudget = 0
        it.forEach {
            it.let { totalBudget += it!!.balance }
        }
        root.txtBudget.text = "$ $totalBudget"
    }

    override fun setUpRecyclerView(rcView: RecyclerView) {
        adapter = RecyclerViewAdapter(
            context!!,
            R.layout.rc_view_item_account_wallet,
            ::setValuesToItemsRecyclerView
        )
        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(context!!)
        rcView.setHasFixedSize(true)

        activity!!.customBottomBar.post {
            val margins = (rcView.layoutParams as RelativeLayout.LayoutParams).apply {
                bottomMargin = (activity!!.customBottomBar.height)
            }
            rcView.layoutParams = margins
        }
    }

    override fun setUpObservers() {
        items.observe(viewLifecycleOwner, Observer {
            adapter.setData(it!!)
            setTotalBudget(it)
        })
    }

    override fun fabClick() {
        activity?.fabMain?.setOnClickListener {
            FullScreenDialogFactory().getFullScreenDialogAddAccountWallet(
                viewModel,
                parentFragmentManager
            )
        }
    }

    override fun setRecyclerViewItemClickListener() {

    }

    override fun setUpDependencies() {
        val dao = Wallet29RoomDatabase.getDatabase(context = context!!).accountWalletDao()
        viewModel.repository = AccountWalletRepository(dao)
    }

    override fun <T> setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapter.ViewHolder,
        items: List<T>,
        position: Int
    ) {
        val elements = holder.account.getViewHolderItemAccount()

        val mItems = items.sortedByDescending {
            it as AccountWalletEntity
            it.updatedAt
        }
        val current = mItems[position] as AccountWalletEntity

        (elements["txtAccountName"] as TextView).text = current.name
        (elements["txtAccountBudget"] as TextView).text = "$ ${current.balance}"
        (elements["txtAccountType"] as TextView).text = current.type

        val colorStateList = ColorStateList(arrayOf(intArrayOf(android.R.attr.state_enabled)), intArrayOf(current.color))
        (elements["cardViewContainerImageAccountIcon"] as CardView).backgroundTintList = colorStateList
        (elements["imgViewAccountIcon"] as ImageView).setImageDrawable(context!!.getDrawable(current.icon))

    }

}
