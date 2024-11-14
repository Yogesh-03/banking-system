package com.news2day.yadavbankingservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.databinding.FragmentUpiPaymentPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpiPaymentPinFragment : Fragment() {
    private lateinit var binding: FragmentUpiPaymentPinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpiPaymentPinBinding.inflate(layoutInflater, container, false)

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_upiPaymentPinFragment_to_finalPaymentFragment)
        }
        return binding.root
    }

}
