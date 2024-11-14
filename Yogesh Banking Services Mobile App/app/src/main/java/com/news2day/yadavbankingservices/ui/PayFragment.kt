package com.news2day.yadavbankingservices.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.news2day.yadavbankingservices.R
import com.news2day.yadavbankingservices.adapter.HomeAdapter
import com.news2day.yadavbankingservices.adapter.PayeeAdapter
import com.news2day.yadavbankingservices.databinding.FragmentPayBinding
import com.news2day.yadavbankingservices.models.Payee
import com.news2day.yadavbankingservices.models.QuickPayment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayFragment : Fragment() {
    private lateinit var binding: FragmentPayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPayBinding.inflate(layoutInflater, container, false)
        // Sample data
        val quickPayments = listOf(
            QuickPayment(R.drawable.baseline_add_home_24, "UPI Payment"),
            QuickPayment(R.drawable.baseline_add_home_24, "Money Transfer"),
            QuickPayment(R.drawable.baseline_add_home_24, "Loan Payment")
        )
        setUpPaymentsMethodRecyclerView(quickPayments)

        val payee = mutableListOf(
            Payee("Yogesh"),
            Payee("Nitish"),
            Payee("Chinmay"),
            Payee("Harshit"),
            Payee("Ashish"),
            Payee("Haniya"),
            Payee("Shruti"),
            Payee("Vishnu")
        )
        setUpPayeeRecyclerView(payee)

        val accounts = listOf(
            "5647 6453 7564 8564",
            "1234 5678 9123 4567",
            "9876 5432 1098 7654",
        )

        // Create an ArrayAdapter using the custom layout
        val accountAdapter = object : ArrayAdapter<String>(
            requireContext(), R.layout.spinner_item, accounts
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val account = accounts[position]
                val textView = view.findViewById<TextView>(R.id.spinnerText)
                textView.text = "${account})"
                return view
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                val account = accounts[position]
                val textView = view.findViewById<TextView>(R.id.spinnerText)
                textView.text = "${account})"
                return view
            }
        }


        // Apply the adapter to the Spinner
        binding.accountSpinner.adapter = accountAdapter

        binding.ibAddPayee.setOnClickListener {
            showAddPayeeDialog(payee)
        }

        return binding.root
    }

    private fun showAddPayeeDialog(list: MutableList<Payee>) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_payee)

        val name = dialog.findViewById<EditText>(R.id.etPayeeName)
        val nickName = dialog.findViewById<EditText>(R.id.etPayeeNickName)
        val accountNumber = dialog.findViewById<EditText>(R.id.etPayeeAccouontNumber)
        val upiId = dialog.findViewById<EditText>(R.id.etPayeeUpiId)
        val addButton = dialog.findViewById<AppCompatButton>(R.id.btnAddPayee)

        addButton.setOnClickListener {
            if (name.text.isNullOrEmpty() || nickName.text.isNullOrEmpty() || accountNumber.text.isNullOrEmpty() || upiId.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "All fields required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val payee = Payee(nickName.text.toString())
            list.add(payee)
            binding.rvPayee.adapter?.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.show()
    }

    private fun setUpPaymentsMethodRecyclerView(list: List<QuickPayment>) = with(binding) {
        rvPaymentsMethod.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvPaymentsMethod.adapter = HomeAdapter().apply {
            submitList(list)
            onClick = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                if (it == "UPI Payment") {
                    startActivity(Intent(requireContext(), PaymentActivity::class.java))
                }
            }
        }
    }

    private fun setUpPayeeRecyclerView(list: List<Payee>) = with(binding) {
//        rvPayee.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvPayee.layoutManager = GridLayoutManager(requireContext(), 4)

        rvPayee.adapter = PayeeAdapter().apply {
            submitList(list)
            onClick = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

            }
        }
    }
}