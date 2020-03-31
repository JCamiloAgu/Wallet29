package com.camilo.wallet29.ui.categories

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camilo.wallet29.R
import com.camilo.wallet29.adapters.RecyclerViewAdapter
import com.camilo.wallet29.common.Constants
import com.camilo.wallet29.common.toast
import com.camilo.wallet29.dialogs.BottomSheetTransactions
import com.camilo.wallet29.enums.TransactionsType
import com.camilo.wallet29.factories.FullScreenDialogFactory
import com.camilo.wallet29.interfaces.FragmentsView
import com.camilo.wallet29.local_data.Wallet29RoomDatabase
import com.camilo.wallet29.local_data.entity.CategoryEntity
import com.camilo.wallet29.repository.CategoryRepository
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_activity_main.*
import kotlinx.android.synthetic.main.fragment_categories.view.*

class CategoriesFragment : FragmentsView<CategoryEntity>() {

    override val viewModel: CategoriesViewModel by viewModels()

    lateinit var adapter: RecyclerViewAdapter

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_categories, container, false)

        setUpDependencies()
        activity!!.customBottomBar.visibility = View.GONE
        activity!!.fabMain.visibility = View.GONE

        root.fabCategories.visibility = View.VISIBLE

        items = viewModel.categories

        setUpRecyclerView(root.rcViewCategories)
        setUpObservers()

        fabClick()

        return root
    }

    override fun setUpRecyclerView(rcView: RecyclerView) {
        adapter = RecyclerViewAdapter(
            context!!,
            R.layout.rc_view_item_categories,
            ::setValuesToItemsRecyclerView,
            ::setRecyclerViewItemClickListener
        )
        rcView.adapter = adapter
        rcView.layoutManager = GridLayoutManager(context!!, 4)
        rcView.setHasFixedSize(true)

    }

    override fun setUpObservers() {
        items.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                viewModel.populateCategories()
            } else {
                adapter.setData(it)
            }
        })
    }

    override fun fabClick() {
        root.fabCategories.setOnClickListener {
            FullScreenDialogFactory().getFullScreenDialogAddCategory(
                viewModel,
                parentFragmentManager,
                categoryEntity = null
            )
        }
    }

    override fun setRecyclerViewItemClickListener(itemPosition: Int) {
        val bottomSheetDialog = BottomSheetBuilder(context).apply {
            setMode(BottomSheetBuilder.MODE_GRID)
            setMenu(R.menu.bottom_sheet_actions_category_menu)
            setItemClickListener {
                menuItemClick(it, itemPosition)
            }
        }.createDialog()

        bottomSheetDialog.show()
    }

    private fun menuItemClick(menuItem: MenuItem, itemPosition: Int) {
        if (menuItem.itemId == R.id.action_insert_category) {
            //TODO = Primero debo hacer que en el BottomTransaction se pueda cambiar la cuenta la categoría
//            BottomSheetTransactions(TransactionsType.WITHDRAW, viewModel, items.value[itemPosition]!!).show(
//                parentFragmentManager,
//                "BottomSheetTransactions"
//            )
        } else if (menuItem.itemId == R.id.action_edit_category) {
            if (items.value!![itemPosition]!!.canModify) {
                FullScreenDialogFactory().getFullScreenDialogAddCategory(
                    viewModel,
                    parentFragmentManager,
                    items.value!![itemPosition]
                )
            } else
                activity!!.toast("Ese elemento no se puede modificar")

        } else if (menuItem.itemId == R.id.action_transactions_category)
            findNavController().navigate(R.id.action_global_navigation_transaction)
        else if (menuItem.itemId == R.id.action_delete_category) {
            if (items.value!![itemPosition]!!.canModify)
                viewModel.delete(items.value!![itemPosition]!!)
            else
                activity!!.toast("Ese elemento no se puede eliminar")
        }
    }


    override fun <T> setValuesToItemsRecyclerView(
        holder: RecyclerViewAdapter.ViewHolder,
        items: List<T>,
        position: Int
    ) {
        val elements = holder.account.getViewHolderItemCategory()

        val current = items[position] as CategoryEntity
        val drawable = Constants.changeDrawableColor(
            context!!,
            Constants.icons[current.iconId]!!.drawable
        )

        (elements["txtCategoryMainName"] as TextView).text = current.name
        (elements["imgCategoryMainIcon"] as ImageView).setImageDrawable(drawable)

        // TODO - Calcular los gastos obteniendo los datos de las transacciones del viewmodel de la misma
        (elements["txtCategoryMainSpends"] as TextView).text = "$ 0"

        val colorStateList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(current.color)
        )
        (elements["rootCardCategory"] as CardView).backgroundTintList =
            colorStateList

    }

    override fun setUpDependencies() {
        val dao =
            Wallet29RoomDatabase.getDatabase(context = context!!)
                .categoryDao()
        viewModel.repository = CategoryRepository(dao)
    }

}
