package com.news2day.yadavbankingservices.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news2day.yadavbankingservices.databinding.QuickPaymentBinding
import com.news2day.yadavbankingservices.models.QuickPayment

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeStartViewHolder>() {

    private lateinit var list: List<QuickPayment>
    private var selectedPosition: Int = -1

    var onClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.HomeStartViewHolder {
        return HomeStartViewHolder(
            QuickPaymentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun submitList(list: List<QuickPayment>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeStartViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HomeStartViewHolder(private val binding: QuickPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.sivQuickPayment.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = bindingAdapterPosition

                if (previousPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousPosition)
                }
//
                notifyItemChanged(selectedPosition)

                onClick?.invoke(list[selectedPosition].title)
            }
        }

        fun bind() {
            val listItem = list[bindingAdapterPosition]

            with(binding) {
                ivQuickPayment.setImageResource(listItem.image)
                tvQuickPayment.text = listItem.title
            }
        }
    }


}