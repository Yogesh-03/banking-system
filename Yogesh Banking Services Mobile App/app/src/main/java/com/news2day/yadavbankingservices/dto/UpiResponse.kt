package com.news2day.yadavbankingservices.dto

import java.sql.Timestamp
import java.time.LocalDateTime

data class UpiResponse(
    val status : String,
    val responseCode : String,
    val transactionId : String?,
    val timestamp: LocalDateTime
)
