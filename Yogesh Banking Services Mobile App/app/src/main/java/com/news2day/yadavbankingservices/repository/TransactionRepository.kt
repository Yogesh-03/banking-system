package com.news2day.yadavbankingservices.repository

import com.news2day.yadavbankingservices.dto.UpiRequestDto
import com.news2day.yadavbankingservices.dto.UpiResponse
import com.news2day.yadavbankingservices.models.Transaction

interface TransactionRepository {
    suspend fun getTransactions(accountNumber : String, startDate:String, endDate:String, callback: TransactionRepositoryImpl.GetTransactionsCallback)
    suspend fun initiateUpiPayment(upiRequestDto: UpiRequestDto, callback : TransactionRepositoryImpl.GetUpiPaymentCallback)
}