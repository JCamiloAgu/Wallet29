package com.camilo.wallet29.local_data

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.camilo.wallet29.local_data.dao.AccountWalletDao
import com.camilo.wallet29.local_data.dao.CategoryDao
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.local_data.entity.CategoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.camilo.wallet29.R


@Database(entities = [AccountWalletEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Wallet29RoomDatabase : RoomDatabase() {

    abstract fun accountWalletDao(): AccountWalletDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: Wallet29RoomDatabase? = null

        fun getDatabase(
            context: Context
        ): Wallet29RoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Wallet29RoomDatabase::class.java,
                    "wallet_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
