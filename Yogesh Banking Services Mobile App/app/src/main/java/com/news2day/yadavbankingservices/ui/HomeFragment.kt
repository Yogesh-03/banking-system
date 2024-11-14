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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.adapter.HomeAdapter
import com.news2day.yadavbankingservices.databinding.FragmentHomeBinding
import com.news2day.yadavbankingservices.dto.AccountInfo
import com.news2day.yadavbankingservices.dto.UserAccountEnquiryDto
import com.news2day.yadavbankingservices.models.QuickPayment
import com.news2day.yadavbankingservices.viewmodel.AuthViewModel
import com.news2day.yadavbankingservices.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("user_details", MODE_PRIVATE)

        // Retrieve the JSON string from SharedPreferences
        val json = sharedPreferences.getString("account_info", null)

        val gson = Gson()
        val accountInfo = gson.fromJson(json, AccountInfo::class.java)
        val userAccountEnquiryDto = UserAccountEnquiryDto(accountNumber = accountInfo.accountNumber)


        // Sample data
        val quickPayments = listOf(
            QuickPayment(R.drawable.baseline_add_home_24, "UPI Payment"),
            QuickPayment(R.drawable.baseline_add_home_24, "Money Transfer")
        )
        setUpHomeAdapterRecyclerView(quickPayments)



        viewModel.getUserAccountEnquiryDetails(userAccountEnquiryDto)

        viewModel.userAccountEnquiryResult.observe(requireActivity(), Observer {
            if (it == null) {
                Toast.makeText(requireContext(), "Forbidden - 403", Toast.LENGTH_SHORT).show()
            } else {
                binding.apply {
                    tvNameFirst.text = it.accountInfo?.accountName?.split(" ")?.get(0) ?: "Name"
                    tvNameFull.text = it.accountInfo?.accountName ?: "Name"
                    tvAccountBalance.text = it.accountInfo?.accountBalance ?: "0.00"
                    tvAccountNumber.text =
                        "XXXX XXXX XXXX ${it.accountInfo?.accountNumber?.takeLast(4)}"
                }
            }
        })

        return binding.root
    }

    private fun setUpHomeAdapterRecyclerView(list: List<QuickPayment>) = with(binding) {
        rvQuickPayments.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvQuickPayments.adapter = HomeAdapter().apply {
            submitList(list)
            onClick = {
                if (it == "UPI Payment"){
                    startActivity(Intent(requireActivity(), PaymentActivity::class.java))
                }
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}