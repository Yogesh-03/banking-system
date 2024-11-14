package com.news2day.yadavbankingservices.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news2day.yadavbankingservices.dto.BankResponse
import com.news2day.yadavbankingservices.dto.UserAccountEnquiryDto
import com.news2day.yadavbankingservices.repository.UserRepository
import com.news2day.yadavbankingservices.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userAccountEnquiryResult = MutableLiveData<BankResponse?>(null)
    val userAccountEnquiryResult: LiveData<BankResponse?> = _userAccountEnquiryResult

    fun getUserAccountEnquiryDetails(userAccountEnquiryDto: UserAccountEnquiryDto) =
        viewModelScope.launch {
            userRepository.userAccountEnquiry(
                userAccountEnquiryDto,
                object : UserRepositoryImpl.UserAccountEnquiryCallback {
                    override fun onAccountEnquiryResponse(response: BankResponse?) {
                        _userAccountEnquiryResult.value = response
                    }
                })
        }
}