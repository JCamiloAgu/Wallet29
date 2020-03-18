package com.camilo.wallet29.ui.configurations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfigurationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is configurations Fragment"
    }
    val text: LiveData<String> = _text
}