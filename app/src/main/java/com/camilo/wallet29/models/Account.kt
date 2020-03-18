package com.camilo.wallet29.models

data class Account(
    val accountId: Int?,
    val accountName: String,
    val accountBalance: Int,
    val accountType: String,
    val accountDescription: String?,
    val color: Int? = null,
    val icon: Int? = null
)