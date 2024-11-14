package com.news2day.yadavbankingservices.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news2day.yadavbankingservices.databinding.ItemTransactionBinding
import com.news2day.yadavbankingservices.models.QuickPayment
import com.news2day.yadavbankingservices.models.Transaction

class TransactionsAdapter : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {
    private lateinit var list: List<Transaction>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun submitList(list: List<Transaction>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            val transaction = list[bindingAdapterPosition]
            with(binding) {
               transactionDate.text = transaction.createdAt.toString()
                transactionTransferTo.text = transaction.accountNumber
                transactionAmount.text = "- ${transaction.amount}"
            }
        }
    }
}