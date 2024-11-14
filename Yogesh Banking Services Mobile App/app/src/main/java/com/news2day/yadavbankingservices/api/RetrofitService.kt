package com.news2day.yadavbankingservices.api

import com.news2day.yadavbankingservices.dto.BankResponse
import com.news2day.yadavbankingservices.dto.LoginDto
import com.news2day.yadavbankingservices.dto.LoginResult
import com.news2day.yadavbankingservices.dto.UpiRequestDto
import com.news2day.yadavbankingservices.dto.UpiResponse
import com.news2day.yadavbankingservices.dto.UserAccountEnquiryDto
import com.news2day.yadavbankingservices.models.Transaction
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @POST("/api/user/login")
    fun executeLogin(@Body loginDto: LoginDto): Call<LoginResult>

    @POST("/api/user/userAccountEnquiry")
    fun userAccountEnquiry(@Body userAccountEnquiryDto: UserAccountEnquiryDto): Call<BankResponse>

    @GET("/generateTransactions")
    fun getTransactions(
        @Query("accountNumber") accountNumber: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ) : Call<List<Transaction>>

    @POST("/initiateUpiPayment")
    fun initiateUpiPayment(@Body upiRequestDto: UpiRequestDto) : Call<UpiResponse>


}