package com.news2day.yadavbankingservices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.adapter.TransactionsAdapter
import com.news2day.yadavbankingservices.databinding.FragmentTransactionsBinding
import com.news2day.yadavbankingservices.models.Transaction
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Date

@AndroidEntryPoint
class TransactionsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTransactionsBinding.inflate(layoutInflater, container, false)
        val transactionList = getTransactionList()
        setUpTransactionRecyclerView(transactionList)
        return binding.root
    }

    private fun setUpTransactionRecyclerView(list: List<Transaction>) = with(binding) {
        rvTransactions.layoutManager = LinearLayoutManager(requireContext())
        rvTransactions.adapter = TransactionsAdapter().apply {
            submitList(list)
        }
    }

    private fun getTransactionList(): List<Transaction> {

        return listOf(
            Transaction(
                "TXN001",
                "Deposit",
                BigDecimal("1000.00"),
                "2024515257",
                "Completed",
                LocalDate.parse("2024-10-01"),
                LocalDate.parse("2024-10-01")
            ),
            Transaction(
                "TXN002",
                "Withdrawal",
                BigDecimal("500.00"),
                "2024515257",
                "Completed",
                LocalDate.parse("2024-10-01"),
                LocalDate.parse("2024-10-01")
            ),
            Transaction(
                "TXN003",
                "Transfer",
                BigDecimal("250.00"),
                "2024515257",
                "Pending",
                LocalDate.parse("2024-10-01"),
                LocalDate.parse("2024-10-01")
            ),
            Transaction(
                "TXN004",
                "Deposit",
                BigDecimal("1500.00"),
                "2024515257",
                "Completed",
                LocalDate.parse("2024-10-01"),
                LocalDate.parse("2024-10-01")
            ),
            Transaction(
                "TXN005",
                "Withdrawal",
                BigDecimal("700.00"),
                "2024515257",
                "Failed",
                LocalDate.parse("2024-10-01"),
                LocalDate.parse("2024-10-01")
            ),
        )
    }
}