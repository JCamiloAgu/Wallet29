package com.camilo.wallet29.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.camilo.wallet29.R
import com.camilo.wallet29.common.isValidInput
import com.camilo.wallet29.common.text
import com.camilo.wallet29.common.toast
import com.camilo.wallet29.common.validate
import com.camilo.wallet29.interfaces.FullScreenDialog
import com.camilo.wallet29.local_data.entity.CategoryEntity
import com.camilo.wallet29.ui.categories.CategoriesViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.add_element_dialog_header.view.*

class DialogAddCategory(
    private val viewModel: CategoriesViewModel,
    override var color: Int,
    private val categoryEntity: CategoryEntity?
) : FullScreenDialog() {

    lateinit var name: TextInputLayout

    override var iconId: Int = categoryEntity?.iconId ?: 0

    override fun display(fragmentManager: FragmentManager?): FullScreenDialog? {
        val fullScreen = DialogAddCategory(viewModel, color, categoryEntity)
        fullScreen.show(fragmentManager!!, TAG)
        return fullScreen
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.add_element_dialog_header, container, false)
        toolbar = view.addElementDialogToolbar
        name = view.edtNameElement

        if (categoryEntity != null) {
            name.editText!!.setText(categoryEntity.name)
            changeFabIcon(view.fabCustomizeElement)
        }

        name.editText!!.validate {
            name.error =
                if (isValidInput(it)) null else getString(R.string.input_lenght_error)
        }

        name.post {
            changeColors()
        }

        return view
    }

    override fun menuItemClick() {
        if (isAllInputsCorrect()) {
            if (categoryEntity == null) {
                insert()
                activity!!.toast(getString(R.string.toast_add_category))
            } else {
                update()
                activity!!.toast(getString(R.string.toast_update_category))
            }
            dismiss()
        }
    }

    override fun toolbarNavigationItemClick() {
        if (categoryEntity != null) {
            if (name.text() != categoryEntity.name)
                showDialogDiscardChanges()
            else
                dismiss()
        } else {
            if (isValidInput(name.text()))
                showDialogDiscardChanges()
            else
                dismiss()
        }
    }

    override fun isAllInputsCorrect(): Boolean =
        if (!isValidInput(name.text())) {
            name.error = getString(R.string.input_lenght_error)
            false
        } else
            true


    override fun insert() {
        viewModel.insert(
            CategoryEntity(
                id = 0,
                name = view?.edtNameElement!!.text(),
                canModify = true,
                color = color,
                iconId = iconId
            )
        )
    }

    override fun update() {
        categoryEntity!!.name = view?.edtNameElement!!.text()
        categoryEntity.color = color
        categoryEntity.iconId = iconId

        viewModel.update(categoryEntity)
    }
}