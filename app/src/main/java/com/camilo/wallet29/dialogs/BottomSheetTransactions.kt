package com.camilo.wallet29.dialogs

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.camilo.wallet29.R
import com.camilo.wallet29.enums.TransactionsType
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.ui.account_wallet.AccountWalletViewModel
import com.camilo.wallet29.ui.calculator.Calculator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.bottom_sheet_dialog_insert_withdraw.view.*


class BottomSheetTransactions(
    private val transactionsType: TransactionsType,
    private val viewModel: AccountWalletViewModel,
    private val accountWalletEntity: AccountWalletEntity
) :
    BottomSheetDialogFragment() {

    lateinit var edtTransactionValue: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.bottom_sheet_dialog_insert_withdraw, container, false)
        edtTransactionValue = root.edtTransactionValue

        edtTransactionValue.post {
            setUpUi()
        }

        setUpCalculator(root)
        return root
    }

    private fun setUpUi() {
        var color: Int? = null
        val textTransactionsType: String

        when (transactionsType) {
            TransactionsType.INSERT -> {
                color = R.color.bottom_sheet_transaction_text_insert
                textTransactionsType = getString(R.string.transaction_type_insert)
            }
            TransactionsType.WITHDRAW -> {
                color = R.color.bottom_sheet_transaction_text_withdraw
                textTransactionsType = getString(R.string.transaction_type_withdraw)
            }
            else -> textTransactionsType = getString(R.string.transaction_type_transfer)
        }

        if (color != null) {
            val colorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.stateNotNeeded)),
                intArrayOf(color)
            )
            edtTransactionValue.editText!!.setTextColor(color)
            edtTransactionValue.hintTextColor = colorStateList
        }

        edtTransactionValue.hint = textTransactionsType
    }

    private fun setUpCalculator(root: View) {
        val calculator = Calculator()

        with(root) {
            Cero.setOnClickListener { calculator.addValue('0') }
            Uno.setOnClickListener { calculator.addValue('1') }
            Dos.setOnClickListener { calculator.addValue('2') }
            Tres.setOnClickListener { calculator.addValue('3') }
            Cuatro.setOnClickListener { calculator.addValue('4') }
            Cinco.setOnClickListener { calculator.addValue('5') }
            Seis.setOnClickListener { calculator.addValue('6') }
            Siete.setOnClickListener { calculator.addValue('7') }
            Ocho.setOnClickListener { calculator.addValue('8') }
            Nueve.setOnClickListener { calculator.addValue('9') }

            Comma.setOnClickListener { calculator.btnCommaOnClickListener() }

            Suma.setOnClickListener { calculator.btnSumaOnClickListener() }
            Resta.setOnClickListener { calculator.btnRestaOnClickListener() }
            Multiplica.setOnClickListener { calculator.btnMultiplicaOnClickListener() }
            Divide.setOnClickListener { calculator.btnDivideOnClickListener() }

            Clean.setOnClickListener { calculator.btnCleanOnClickListener() }
            Borrar.setOnClickListener { calculator.btnBorrarOnClickListener() }
            Igual.setOnClickListener { calculator.doOperation() }
        }

        calculator.resultado.observe(viewLifecycleOwner, Observer {
            edtTransactionValue.editText!!.setText("$ $it")
        })

        calculator.operador.observe(viewLifecycleOwner, Observer {
            if (it != "") {
                root.Igual.setImageResource(R.drawable.ic_equal_24dp)
                root.Igual.setOnClickListener { calculator.doOperation() }
            } else {
                root.Igual.setImageResource(R.drawable.ic_done_24dp)
                root.Igual.setOnClickListener { saveTransaction(calculator.resultado.value!!) }
            }
        })

    }

    // TODO - Falta configurar la TRANSFER y poner a funcionar los dos botones superiores
    private fun saveTransaction(totalTransaction: String) {
        if (transactionsType == TransactionsType.INSERT)
            accountWalletEntity.balance += totalTransaction.toDouble()
        else if(transactionsType == TransactionsType.WITHDRAW)
            accountWalletEntity.balance -= totalTransaction.toDouble()

        viewModel.update(accountWalletEntity)
        dismiss()
    }


}