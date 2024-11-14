package com.news2day.yadavbankingservices.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.news2day.yadavbankingservices.utils.JwtTokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitInstance {
    companion object {
        private const val BASE_URL = "http://172.31.110.11:8080"

        // Get Retrofit instance with Authorization header
        fun getRetrofitInstance(context: Context): Retrofit {
            val tokenManager = JwtTokenManager(context)  // Access token from SharedPreferences
            val token = tokenManager.getToken() ?: ""

            // Add an interceptor to include the JWT in the headers
            val client = OkHttpClient.Builder()
                .addInterceptor { chain: Interceptor.Chain ->
                    val request: Request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer $token")  // Add token to header
                        .addHeader("Content-Type", "application/json")
                        .build()
                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)  // Set the OkHttpClient with the interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
