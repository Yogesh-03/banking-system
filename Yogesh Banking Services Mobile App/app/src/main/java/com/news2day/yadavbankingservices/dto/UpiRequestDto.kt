package com.news2day.yadavbankingservices.dto

data class UpiRequestDto(
    val accountNumber : String,
    val virtualPaymentAddress : String,
    val amount : String
)
