package com.camilo.wallet29.factories

import androidx.fragment.app.FragmentManager
import com.camilo.wallet29.dialogs.DialogAddAccountWallet
import com.camilo.wallet29.dialogs.DialogAddCategory
import com.camilo.wallet29.interfaces.FullScreenDialog
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.local_data.entity.CategoryEntity
import com.camilo.wallet29.ui.account_wallet.AccountWalletViewModel
import com.camilo.wallet29.ui.categories.CategoriesViewModel

class FullScreenDialogFactory {
    fun getFullScreenDialogAddAccountWallet(
        viewModel: AccountWalletViewModel,
        fragmentManager: FragmentManager,
        accountWalletEntity: AccountWalletEntity?
    ): FullScreenDialog? {
        return DialogAddAccountWallet(viewModel, color = accountWalletEntity?.color ?: -688361,accountWalletEntity = accountWalletEntity).display(fragmentManager)
    }

    fun getFullScreenDialogAddCategory(
        viewModel: CategoriesViewModel,
        fragmentManager: FragmentManager,
        categoryEntity: CategoryEntity?
    ): FullScreenDialog? {
        return DialogAddCategory(viewModel, color = categoryEntity?.color ?: -688361, categoryEntity = categoryEntity).display(fragmentManager)
    }


}