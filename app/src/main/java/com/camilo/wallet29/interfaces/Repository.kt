package com.camilo.wallet29.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.camilo.wallet29.local_data.entity.AccountWalletEntity

interface Repository <T, F> {
    val dao: T

    fun getAllData(): LiveData<List<F?>>
    suspend fun insert(entity: F)
    suspend fun update(entity: F)
    suspend fun delete(entity: F)
}

