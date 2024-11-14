package com.news2day.yadavbankingservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.databinding.FragmentUpiPayment2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpiPaymentFragment2 : Fragment() {
    private lateinit var bindig : FragmentUpiPayment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindig = FragmentUpiPayment2Binding.inflate(layoutInflater, container, false)

        bindig.btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_upiPaymentFragment2_to_upiPaymentPinFragment)
        }

        return bindig.root
    }


}