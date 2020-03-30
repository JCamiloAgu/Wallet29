package com.camilo.wallet29

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camilo.wallet29.local_data.entity.AccountWalletEntity

class MainViewModel : ViewModel() {

    val accountsFilter: MutableLiveData<String> = MutableLiveData("")
    var accountsWallet: MutableLiveData<List<AccountWalletEntity?>>? = null


}