package com.news2day.yadavbankingservices.dto

data class BankResponse(
    val responseCode:String ,
    val responseMessage:String ,
    val accountInfo: AccountInfo?
)
