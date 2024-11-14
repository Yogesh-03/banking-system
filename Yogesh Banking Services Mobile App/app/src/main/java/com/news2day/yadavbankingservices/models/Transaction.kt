package com.news2day.yadavbankingservices.models

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(
    val transactionId : String,
    val transactionType: String,
    val amount:BigDecimal,
    val accountNumber : String,
    val status: String,
    val createdAt : LocalDate,
    val modifiedAt : LocalDate
)