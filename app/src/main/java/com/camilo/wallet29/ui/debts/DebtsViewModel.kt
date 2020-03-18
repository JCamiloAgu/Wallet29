package com.camilo.wallet29.ui.debts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DebtsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is debts Fragment"
    }
    val text: LiveData<String> = _text
}