package com.news2day.yadavbankingservices.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.news2day.yadavbankingservices.api.RetrofitInstance
import com.news2day.yadavbankingservices.api.RetrofitService
import com.news2day.yadavbankingservices.databinding.FragmentLoginBinding
import com.news2day.yadavbankingservices.dto.AccountInfo
import com.news2day.yadavbankingservices.dto.LoginDto
import com.news2day.yadavbankingservices.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var retrofitService: RetrofitService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("user_details", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val masterKey = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val encryptedPrefs = EncryptedSharedPreferences.create(
            requireContext(),
            "encrypted_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


        retrofitService = RetrofitInstance.getRetrofitInstance(requireContext()).create(RetrofitService::class.java)

        binding.btnLoginContinue.setOnClickListener {
            val loginDto = LoginDto(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
            viewModel.login(loginDto)
        }

        viewModel.loginResult.observe(requireActivity(), Observer {
            if (it == null) {
                Toast.makeText(requireContext(), "Forbidden - 403", Toast.LENGTH_SHORT).show()
            } else if (it.responseCode == "Login Success") {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                encryptedPrefs.edit().putString("jwt_token", it.responseMessage).apply()

                val accountInfo = it.accountInfo
                // Convert AccountInfo object to JSON
                val gson = Gson()
                val json = gson.toJson(accountInfo)

                // Save the JSON string to SharedPreferences
                editor.putString("account_info", json)
                editor.apply()  // Don't forget to apply the changes

                startActivity(Intent(requireActivity(), HomeActivity::class.java))

            }
        })



        return binding.root
    }


}