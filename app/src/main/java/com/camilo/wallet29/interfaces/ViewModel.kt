package com.camilo.wallet29.interfaces

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class ViewModel<T, F>(application: Application): AndroidViewModel(application) {

    abstract val repository: T

    abstract fun insert(entity: F)
    abstract fun update(entity: F)
    abstract fun delete(entity: F)
}