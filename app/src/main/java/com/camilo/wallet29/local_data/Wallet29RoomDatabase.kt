package com.camilo.wallet29.local_data

import android.content.Context
import androidx.room.*
import com.camilo.wallet29.local_data.dao.AccountWalletDao
import com.camilo.wallet29.local_data.entity.AccountWalletEntity

@Database(entities = [AccountWalletEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Wallet29RoomDatabase : RoomDatabase() {

    abstract fun accountWalletDao(): AccountWalletDao

    companion object {

        @Volatile
        private var INSTANCE: Wallet29RoomDatabase? = null

        fun getDatabase(context: Context): Wallet29RoomDatabase {
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