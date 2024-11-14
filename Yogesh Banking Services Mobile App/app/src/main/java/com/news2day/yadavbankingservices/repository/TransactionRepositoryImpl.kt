package com.news2day.yadavbankingservices.repository

import com.news2day.yadavbankingservices.api.RetrofitService
import com.news2day.yadavbankingservices.dto.UpiRequestDto
import com.news2day.yadavbankingservices.dto.UpiResponse
import com.news2day.yadavbankingservices.models.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) :
    TransactionRepository {

    interface GetTransactionsCallback {
        fun onGetTransactionsCallback(response: List<Transaction>?)
    }

    interface GetUpiPaymentCallback {
        fun onGetUpiPaymentCallback(response: UpiResponse?)
    }

    override suspend fun getTransactions(
        accountNumber: String,
        startDate: String,
        endDate: String,
        callback: GetTransactionsCallback
    ) {
        val call: Call<List<Transaction>> =
            retrofitService.getTransactions(accountNumber, startDate, endDate)

        call.enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(
                call: Call<List<Transaction>>,
                response: Response<List<Transaction>>
            ) {
                callback.onGetTransactionsCallback(response.body())
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                callback.onGetTransactionsCallback(null)
            }
        })
    }

    override suspend fun initiateUpiPayment(
        upiRequestDto: UpiRequestDto,
        callback: GetUpiPaymentCallback
    ) {
        val call: Call<UpiResponse> = retrofitService.initiateUpiPayment(upiRequestDto)

        call.enqueue(object : Callback<UpiResponse> {
            override fun onResponse(call: Call<UpiResponse>, response: Response<UpiResponse>) {
                callback.onGetUpiPaymentCallback(response.body())
            }

            override fun onFailure(call: Call<UpiResponse>, t: Throwable) {
                val upiResponse = UpiResponse(
                    status = "ERROR",
                    responseCode = "-1",
                    transactionId = null,
                    timestamp = LocalDateTime.now()
                )
            }

        })
    }
}