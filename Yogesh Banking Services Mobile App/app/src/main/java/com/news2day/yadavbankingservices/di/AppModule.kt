package com.news2day.yadavbankingservices.di

import android.content.Context
import com.news2day.yadavbankingservices.api.RetrofitInstance
import com.news2day.yadavbankingservices.api.RetrofitService
import com.news2day.yadavbankingservices.repository.AuthRepository
import com.news2day.yadavbankingservices.repository.AuthRepositoryImpl
import com.news2day.yadavbankingservices.repository.TransactionRepository
import com.news2day.yadavbankingservices.repository.TransactionRepositoryImpl
import com.news2day.yadavbankingservices.repository.UserRepository
import com.news2day.yadavbankingservices.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideAuthRepository(impl : AuthRepositoryImpl) : AuthRepository = impl

    @Provides
    fun provideRetrofitService(@ApplicationContext context: Context) : RetrofitService {
        return RetrofitInstance.getRetrofitInstance(context).create(RetrofitService::class.java)
    }

    @Provides
    fun provideUserRepository(impl : UserRepositoryImpl) : UserRepository = impl

    @Provides
    fun provideTransactionRepository(impl : TransactionRepositoryImpl) : TransactionRepository = impl
}