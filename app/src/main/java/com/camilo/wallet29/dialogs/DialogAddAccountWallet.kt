package com.camilo.wallet29.dialogs

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
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
    override var color: Int = -688361

) : FullScreenDialog() {

    private lateinit var inputs: Array<TextInputLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    override fun display(fragmentManager: FragmentManager?): FullScreenDialog? {
        val fullScreen = DialogAddAccountWallet(viewModel)
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

        inputs = arrayOf(name, balance, description)

        inputs.forEach { input ->
            input.editText!!.validate {
                input.error =
                    if (isValidInput(it)) null else getString(R.string.input_lenght_error)
            }
        }

        name.post {
            changeColors(color)
        }

        return view
    }

    override fun menuItemClick() {
        if (isAllInputsCorrect()) {
            insert()
            dismiss()
            activity!!.toast(getString(R.string.toast_add_account_wallet))
        }
    }


    override fun toolbarNavigationItemClick() {
        var isInputsChange = false
        for (i in inputs.indices) {
            if(i == 2)
                if(inputs[i].text() != getString(R.string.text_description_none))
                    isInputsChange = true
            if (inputs[i].editText!!.text.isNotEmpty()) {
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

    override fun changeColors(color: Int) {
        super.changeColors(color)

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

        if (dialog != null) {
            val finalColor = ColorUtils.blendARGB(color, Color.BLACK, 0.2f)
            dialog!!.window?.statusBarColor = ColorUtils.setAlphaComponent(finalColor, 255)
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
                balance = view?.edtBalance!!.text().toInt(),
                description = view?.edtDescription!!.text(),
                color = color,
                icon = if (view?.radioButtonCash!!.isChecked) R.drawable.ic_account_balance_wallet_black_24dp else R.drawable.ic_credit_card_white_24dp,
                createdAt = Calendar.getInstance(),
                updatedAt = Calendar.getInstance()
            )
        )
    }
}

