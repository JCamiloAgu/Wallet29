package com.camilo.wallet29.models

import java.time.LocalDate
import java.util.*

data class Transactions(
    val transactionId: Int?,
    val category: String,
    val transactionValue: Int,
    val transactionDate: Calendar,
    val accountAffect: String,
    val color: Int? = null,
    val icon: Int? = null,
    val categoryIcon: Int? = null
)