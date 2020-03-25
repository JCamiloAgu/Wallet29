package com.camilo.wallet29.factories

import androidx.fragment.app.FragmentManager
import com.camilo.wallet29.dialogs.DialogAddAccountWallet
import com.camilo.wallet29.interfaces.FullScreenDialog
import com.camilo.wallet29.ui.account_wallet.AccountWalletViewModel

class FullScreenDialogFactory {
    fun getFullScreenDialogAddAccountWallet(
        viewModel: AccountWalletViewModel,
        fragmentManager: FragmentManager
    ): FullScreenDialog? {
        return DialogAddAccountWallet(viewModel).display(fragmentManager)
    }
}