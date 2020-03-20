package com.camilo.wallet29.models

import java.util.*


data class Saving(
    val id: Int?,
    val name: String,
    val saved: Int,
    val totalToSave: Int,
    val description: String,
    val fromDate: String,
    val untilDate: String,
    val createdAt: Calendar,
    val updatedAt: Calendar? = null,
    val iconSaving: Int? = null
)