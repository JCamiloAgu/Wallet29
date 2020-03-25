package com.camilo.wallet29.repository

import androidx.lifecycle.LiveData
import com.camilo.wallet29.interfaces.Repository
import com.camilo.wallet29.local_data.dao.AccountWalletDao
import com.camilo.wallet29.local_data.entity.AccountWalletEntity

class AccountWalletRepository(override val dao: AccountWalletDao): Repository<AccountWalletDao, AccountWalletEntity> {
    override fun getAllData(): LiveData<List<AccountWalletEntity?>> = dao.getAllData()

    override suspend fun insert(entity: AccountWalletEntity) {
        dao.insert(entity)
    }

    override suspend fun update(entity: AccountWalletEntity) {
        dao.update(entity)
    }

    override suspend fun delete(entity: AccountWalletEntity) {
        dao.delete(entity)
    }


}