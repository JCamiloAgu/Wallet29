package com.camilo.wallet29.local_data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.camilo.wallet29.local_data.entity.AccountWalletEntity

@Dao
interface AccountWalletDao {

    @Query("SELECT * FROM accounts_wallet")
    fun getAllData(): LiveData<List<AccountWalletEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountWalletEntity: AccountWalletEntity)

    @Delete
    suspend fun delete(accountWalletEntity: AccountWalletEntity)

    @Update
    suspend fun update(accountWalletEntity: AccountWalletEntity)
}