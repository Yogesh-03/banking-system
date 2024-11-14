package com.news2day.yadavbankingservices.repository

import com.news2day.yadavbankingservices.dto.UserAccountEnquiryDto

interface UserRepository {
    suspend fun userAccountEnquiry(userAccountEnquiryDto: UserAccountEnquiryDto, callback : UserRepositoryImpl.UserAccountEnquiryCallback)
}