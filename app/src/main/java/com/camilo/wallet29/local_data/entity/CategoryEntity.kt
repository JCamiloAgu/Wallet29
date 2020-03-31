package com.camilo.wallet29.local_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "categories")
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    val canModify: Boolean,
    var color: Int,
    var iconId: Int
)