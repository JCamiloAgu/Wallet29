package com.camilo.wallet29.models

data class Transactions(
    val transactionId: Int?,
    val category: String,
    val transactionValue: Int,
    val transactionDate: String,
    val color: Int? = null,
    val icon: Int? = null
)