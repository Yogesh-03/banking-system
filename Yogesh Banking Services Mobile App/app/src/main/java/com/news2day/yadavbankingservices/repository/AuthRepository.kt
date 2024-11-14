package com.news2day.yadavbankingservices.repository

import com.news2day.yadavbankingservices.dto.LoginDto

interface AuthRepository {
    suspend fun executeLogin(loginDto: LoginDto, callBack: AuthRepositoryImpl.LoginCallBack)
}