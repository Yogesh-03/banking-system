package com.news2day.yadavbankingservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.databinding.FragmentUpiPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpiPaymentFragment : Fragment() {
    private lateinit var binding: FragmentUpiPaymentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpiPaymentBinding.inflate(layoutInflater, container, false)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_upiPaymentFragment_to_upiPaymentFragment2)
        }

//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            findNavController().popBackStack()
//        }

        return binding.root
    }


}