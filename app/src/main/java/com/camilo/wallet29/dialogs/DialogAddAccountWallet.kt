package com.camilo.wallet29.dialogs

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.camilo.wallet29.R
import com.camilo.wallet29.common.*
import com.camilo.wallet29.interfaces.FullScreenDialog
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.ui.account_wallet.AccountWalletViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.add_element_dialog_header.view.*
import kotlinx.android.synthetic.main.dialog_add_account.view.*
import java.util.*


class DialogAddAccountWallet(
    private val viewModel: AccountWalletViewModel,
    override var color: Int,
    private val accountWalletEntity: AccountWalletEntity?

) : FullScreenDialog() {

    private lateinit var inputs: Array<TextInputLayout>

    override var iconId: Int = accountWalletEntity?.iconId ?: 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    override fun display(fragmentManager: FragmentManager?): FullScreenDialog? {
        val fullScreen = DialogAddAccountWallet(viewModel, color, accountWalletEntity)
        fullScreen.show(fragmentManager!!, TAG)
        return fullScreen
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.dialog_add_account, container, false)
        toolbar = view.addElementDialogToolbar

        val name = view.edtNameElement
        val balance = view.edtBalance
        val description = view.edtDescription

        if (accountWalletEntity != null) {
            name.editText!!.setText(accountWalletEntity.name)
            balance.editText!!.setText("${accountWalletEntity.balance}")
            description.editText!!.setText(accountWalletEntity.description)
            changeFabIcon(view.fabCustomizeElement)
        }

        inputs = arrayOf(name, balance, description)

        inputs.forEach { input ->
            input.editText!!.validate {
                input.error =
                    if (isValidInput(it)) null else getString(R.string.input_lenght_error)
            }
        }

        name.post {
            changeColors()
        }

        return view
    }

    override fun menuItemClick() {
        if (isAllInputsCorrect()) {
            if (accountWalletEntity == null) {
                insert()
                activity!!.toast(getString(R.string.toast_add_account_wallet))
            } else {
                update()
                activity!!.toast(getString(R.string.toast_update_account_wallet))
            }
            dismiss()
        }
    }

    override fun toolbarNavigationItemClick() {
        var isInputsChange = false
        for (i in inputs.indices) {
            if (i == 2)
                if (inputs[i].text() != getString(R.string.text_description_none))
                    isInputsChange = true
            if (inputs[i].editText!!.text.isNotEmpty() && i != 2) {
                isInputsChange = true
            }
        }

        if (isInputsChange)
            showDialogDiscardChanges()
        else
            dismiss()
    }

    override fun isAllInputsCorrect(): Boolean {
        var isInputsCorrect = true

        inputs.forEach {
            if (!isValidInput(it.text())) {
                it.error = getString(R.string.input_lenght_error)
                isInputsCorrect = false
            }
        }
        return isInputsCorrect
    }

    override fun changeColors() {
        super.changeColors()

        val colorStateList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(color)
        )
        view!!.apply {
            radioButtonCash.buttonTintList = colorStateList
            radioCreditCard.buttonTintList = colorStateList
            edtBalance.setHintsColor(color)
            edtDescription.setHintsColor(color)
        }
    }

    override fun insert() {
        viewModel.insert(
            AccountWalletEntity(
                id = 0,
                name = view?.edtNameElement!!.text(),
                type = if (view?.radioButtonCash!!.isChecked) getString(R.string.text_cash) else getString(
                    R.string.text_credit_card
                ),
                balance = view?.edtBalance!!.text().toDouble(),
                description = view?.edtDescription!!.text(),
                color = color,
                iconId = iconId,
                createdAt = Calendar.getInstance(),
                updatedAt = Calendar.getInstance()
            )
        )
    }

    override fun update() {
        accountWalletEntity!!.name = view?.edtNameElement!!.text()
        accountWalletEntity.type =
            if (view?.radioButtonCash!!.isChecked) getString(R.string.text_cash) else getString(
                R.string.text_credit_card
            )
        accountWalletEntity.balance = view?.edtBalance!!.text().toDouble()
        accountWalletEntity.description = view?.edtDescription!!.text()
        accountWalletEntity.color = color
        accountWalletEntity.iconId = iconId
        accountWalletEntity.updatedAt = Calendar.getInstance()

        viewModel.update(accountWalletEntity)
    }
}

