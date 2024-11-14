package com.news2day.yadavbankingservices.dto

data class LoginResult(
    val accountInfo: AccountInfo?,
    val responseCode: String,
    val responseMessage: String
)