package com.camilo.wallet29.ui.account_wallet

import android.app.Application
import android.view.View
import android.widget.RadioButton
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.camilo.wallet29.R
import com.camilo.wallet29.interfaces.ViewModel
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.repository.AccountWalletRepository
import kotlinx.coroutines.launch

class AccountWalletViewModel(application: Application): ViewModel<AccountWalletRepository, AccountWalletEntity>(application) {
    override lateinit var repository: AccountWalletRepository

    val accountsFilter: MutableLiveData<String> = MutableLiveData("")

    val data: LiveData<List<AccountWalletEntity?>> by lazy { repository.getAllData() }

    override fun insert(entity: AccountWalletEntity) {
        viewModelScope.launch {
            repository.insert(entity)
        }
    }

    override fun update(entity: AccountWalletEntity) {
        viewModelScope.launch {
            repository.update(entity)
        }
    }

    override fun delete(entity: AccountWalletEntity) {
        viewModelScope.launch {
            repository.delete(entity)
        }
    }

}