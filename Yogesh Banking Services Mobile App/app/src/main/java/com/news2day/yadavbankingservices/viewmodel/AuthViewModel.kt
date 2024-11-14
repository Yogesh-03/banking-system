package com.news2day.yadavbankingservices.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news2day.yadavbankingservices.dto.LoginDto
import com.news2day.yadavbankingservices.dto.LoginResult
import com.news2day.yadavbankingservices.repository.AuthRepository
import com.news2day.yadavbankingservices.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository:AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult?>(null)
    val loginResult: LiveData<LoginResult?> = _loginResult

    fun login(loginDto: LoginDto) = viewModelScope.launch {
        repository.executeLogin(loginDto, object: AuthRepositoryImpl.LoginCallBack{
            override fun onLoginResponse(response: LoginResult?) {
                _loginResult.value = response

            }

        })
    }
}