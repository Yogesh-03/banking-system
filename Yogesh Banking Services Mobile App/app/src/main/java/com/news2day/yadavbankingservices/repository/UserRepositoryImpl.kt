package com.news2day.yadavbankingservices.repository

import com.news2day.yadavbankingservices.api.RetrofitService
import com.news2day.yadavbankingservices.dto.BankResponse
import com.news2day.yadavbankingservices.dto.LoginResult
import com.news2day.yadavbankingservices.dto.UserAccountEnquiryDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) : UserRepository {

    interface UserAccountEnquiryCallback {
        fun onAccountEnquiryResponse(response: BankResponse?)
    }

    override suspend fun userAccountEnquiry(userAccountEnquiryDto: UserAccountEnquiryDto, callback: UserAccountEnquiryCallback) {
        val call: Call<BankResponse> = retrofitService.userAccountEnquiry(userAccountEnquiryDto)

        call.enqueue(object : Callback<BankResponse> {
            override fun onResponse(call: Call<BankResponse>, response: Response<BankResponse>) {
                callback.onAccountEnquiryResponse(response.body())
            }

            override fun onFailure(call: Call<BankResponse>, t: Throwable) {
                val bankResponse = BankResponse(
                    responseCode = "-1",
                    responseMessage = t.message.toString(),
                    accountInfo = null
                )

                callback.onAccountEnquiryResponse(bankResponse)
            }


        })
    }
}