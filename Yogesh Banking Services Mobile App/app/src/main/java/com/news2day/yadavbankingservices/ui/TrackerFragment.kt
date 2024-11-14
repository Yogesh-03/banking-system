package com.news2day.yadavbankingservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.databinding.FragmentTrackerBinding
import com.news2day.yadavbankingservices.databinding.FragmentTransactionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackerFragment : Fragment() {
    private lateinit var binding:FragmentTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrackerBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


}