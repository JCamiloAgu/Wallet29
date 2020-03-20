package com.camilo.wallet29.models

data class Summary(
    val id: Int?,
    val categoryName: String,
    val totalTransaction: Int,
    val numberOfTransactions: Int,
    val percentOfTotal: Int,
    val month: String,
    val year: Int,
    val iconCategory: Int? = null
)