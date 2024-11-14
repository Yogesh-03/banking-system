package com.news2day.yadavbankingservices.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news2day.yadavbankingservices.dto.UpiRequestDto
import com.news2day.yadavbankingservices.dto.UpiResponse
import com.news2day.yadavbankingservices.models.Transaction
import com.news2day.yadavbankingservices.repository.TransactionRepository
import com.news2day.yadavbankingservices.repository.TransactionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val transactionRepository: TransactionRepository) : ViewModel()  {

    private val _userTransactions = MutableLiveData<List<Transaction>?>(null)
    val userTransactions: LiveData<List<Transaction>?> = _userTransactions

    private val _upiPaymentResult = MutableLiveData<UpiResponse?>()
    val upiPaymentResult: LiveData<UpiResponse?> = _upiPaymentResult

    fun getUserTransactions(accountNumber:String, startDate:String, endDate:String) =
        viewModelScope.launch {
            transactionRepository.getTransactions(
                accountNumber, startDate, endDate,
                object : TransactionRepositoryImpl.GetTransactionsCallback {
                    override fun onGetTransactionsCallback(response: List<Transaction>?) {
                        _userTransactions.value = response
                    }

                })
        }

    fun initiateUpiPayment(upiRequestDto: UpiRequestDto) = viewModelScope.launch {
        transactionRepository.initiateUpiPayment(
            upiRequestDto, object :  TransactionRepositoryImpl.GetUpiPaymentCallback {
                override fun onGetUpiPaymentCallback(response: UpiResponse?) {
                    _upiPaymentResult.value = response
                }

            }
        )
    }
}