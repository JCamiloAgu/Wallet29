package com.camilo.wallet29.ui.account_wallet

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camilo.wallet29.R
import com.camilo.wallet29.adapters.RecyclerViewAdapter
import com.camilo.wallet29.common.Constants
import com.camilo.wallet29.dialogs.BottomSheetTransactions
import com.camilo.wallet29.enums.TransactionsType
import com.camilo.wallet29.factories.FullScreenDialogFactory
import com.camilo.wallet29.interfaces.FragmentsView
import com.camilo.wallet29.local_data.Wallet29RoomDatabase
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.repository.AccountWalletRepository
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_activity_main.*
import kotlinx.android.synthetic.main.fragment_account_wallet.view.*

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

        activity!!.customBottomBar.visibility = View.VISIBLE
        activity!!.fabMain.visibility = View.VISIBLE


        items = viewModel.data

        setUpRecyclerView(root.rcViewAccountsWallet)
        setUpObservers()

        fabClick()
        return root
    }

    @SuppressLint("SetTextI18n")
    private fun setTotalBudget(items: List<AccountWalletEntity?>?) {
        items?.let { list ->
            var totalBudget = 0.0

            //Esta variable es para determinar si existen cuentas
            // (Porque la primera vez que se llama el método en null y se crea un efecto visual feo)
            var isAccountNull = true

            list.forEach {
                it?.let {
                    totalBudget += it.balance
                    isAccountNull = false
                }
            }

            if (!isAccountNull) {
                root.txtBudget.text = "$ $totalBudget"
                activity!!.txtAccountBalance.text = "$ $totalBudget"
            }
        }

    }

    override fun setUpRecyclerView(rcView: RecyclerView) {
        adapter = RecyclerViewAdapter(
            context!!,
            R.layout.rc_view_item_account_wallet,
            ::setValuesToItemsRecyclerView,
            ::setRecyclerViewItemClickListener
        )
        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(context!!)
        rcView.setHasFixedSize(true)

        activity!!.customBottomBar.post {
            val margins: RelativeLayout.LayoutParams =
                (rcView.layoutParams as RelativeLayout.LayoutParams).apply {
                    bottomMargin = (activity!!.customBottomBar.height)
                }
            rcView.layoutParams = margins
        }
    }

    override fun setUpObservers() {
        items.observe(viewLifecycleOwner, Observer {
            adapter.setData(it!!)
            setUpSpinnerAccounts(it)
            setTotalBudget(it)
        })

        viewModel.accountsFilter.observe(viewLifecycleOwner, Observer {
            if (it != context!!.getString(R.string.spinner_accounts_all)) {
                val filteredList = mutableListOf<AccountWalletEntity>()
                items.value?.forEach { account ->
                    if (account?.name == it)
                        filteredList.add(account!!)
                }
                setTotalBudget(filteredList)
                adapter.setData(filteredList)
            } else {
                setTotalBudget(items.value)
                adapter.setData(items.value!!)
            }
        })
    }

    private fun setUpSpinnerAccounts(it: List<AccountWalletEntity?>) {
        val spinnerItems: MutableList<String> =
            mutableListOf(context!!.getString(R.string.spinner_accounts_all))

        it.forEach {
            spinnerItems.add(it!!.name)
        }

        activity!!.spinnerAccounts.adapter =
            ArrayAdapter(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                spinnerItems
            )
        activity!!.spinnerAccounts.setSelection(Constants.spinnerItemPosition)

        activity!!.spinnerAccounts.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Constants.spinnerItemPosition = position
                viewModel.accountsFilter.value = parent!!.getItemAtPosition(position).toString()
            }
        }
    }

    override fun fabClick() {
        activity?.fabMain?.setOnClickListener {
            FullScreenDialogFactory().getFullScreenDialogAddAccountWallet(
                viewModel,
                parentFragmentManager,
                accountWalletEntity = null
            )
        }
    }

    override fun setRecyclerViewItemClickListener(itemPosition: Int) {
        val bottomSheetDialog = BottomSheetBuilder(context).apply {
            setMode(BottomSheetBuilder.MODE_GRID)
            setMenu(R.menu.bottom_sheet_actions_menu)
            setItemClickListener {
                menuItemClick(it, itemPosition)
            }
        }.createDialog()

        bottomSheetDialog.show()

    }

    private fun menuItemClick(menuItem: MenuItem, itemPosition: Int) {
        val mItems: List<AccountWalletEntity?> = items.value!!.sortedByDescending {
            it?.updatedAt
        }
        if (menuItem.itemId == R.id.action_insert || menuItem.itemId == R.id.action_withdraw || menuItem.itemId == R.id.action_transfer) {
            val transactionType =
                when (menuItem.itemId) {
                    R.id.action_insert -> TransactionsType.INSERT
                    R.id.action_withdraw -> TransactionsType.WITHDRAW
                    else -> TransactionsType.TRANSFER
                }
            BottomSheetTransactions(transactionType, viewModel, mItems[itemPosition]!!).show(
                parentFragmentManager,
                "BottomSheetTransactions"
            )
        } else if (menuItem.itemId == R.id.action_edit) {

            FullScreenDialogFactory().getFullScreenDialogAddAccountWallet(
                viewModel,
                parentFragmentManager,
                mItems[itemPosition]
            )
        } else if (menuItem.itemId == R.id.action_transactions)
            findNavController().navigate(R.id.action_navigation_account_wallet_to_navigation_transaction)
        else if (menuItem.itemId == R.id.action_delete)
            viewModel.delete(mItems[itemPosition]!!)
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
        val drawable =
            Constants.changeDrawableColor(context!!, Constants.icons[current.iconId]!!.drawable)

        (elements["txtAccountName"] as TextView).text = current.name
        (elements["txtAccountBudget"] as TextView).text = "$ ${current.balance}"
        (elements["txtAccountType"] as TextView).text = current.type

        val colorStateList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(current.color)
        )
        (elements["cardViewContainerImageAccountIcon"] as CardView).backgroundTintList =
            colorStateList
        (elements["imgViewAccountIcon"] as ImageView).setImageDrawable(drawable)

    }

    override fun setUpDependencies() {
        val dao = Wallet29RoomDatabase.getDatabase(
            context = context!!
        ).accountWalletDao()
        viewModel.repository = AccountWalletRepository(dao)
    }

}
