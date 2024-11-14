package com.news2day.yadavbankingservices.repository

import android.util.Log
import com.news2day.yadavbankingservices.api.RetrofitService
import com.news2day.yadavbankingservices.data.Resource
import com.news2day.yadavbankingservices.dto.LoginDto
import com.news2day.yadavbankingservices.dto.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) : AuthRepository {

    interface LoginCallBack {
        fun onLoginResponse(response: LoginResult?)
    }

    override suspend fun executeLogin(loginDto: LoginDto, callBack: LoginCallBack) {
        val call: Call<LoginResult> = retrofitService.executeLogin(loginDto)


        call.enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                callBack.onLoginResponse(response.body())
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                val loginResult = LoginResult(
                    accountInfo = null,
                    responseCode = "-1",
                    responseMessage = "Request Failed: ${t.message}"
                )
                callBack.onLoginResponse(loginResult)
            }

        })
    }
}