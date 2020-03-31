package com.camilo.wallet29.local_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "accounts_wallet")
data class AccountWalletEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var type: String,
    var balance: Double,
    var description: String? = null,
    var color: Int,
    var iconId: Int,
    val createdAt: Calendar,
    var updatedAt: Calendar
)