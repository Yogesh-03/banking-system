package com.news2day.yadavbankingservices.adapter

import android.graphics.Color
import android.renderscript.Script.LaunchOptions
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news2day.yadavbankingservices.databinding.ItemPayeeBinding
import com.news2day.yadavbankingservices.models.Payee
import com.news2day.yadavbankingservices.models.QuickPayment
import com.news2day.yadavbankingservices.utils.TextDrawable

class PayeeAdapter : RecyclerView.Adapter<PayeeAdapter.ViewHolder>() {
    private lateinit var list: List<Payee>
    private var selectedPosition: Int = -1

    var onClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPayeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun submitList(list: List<Payee>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ItemPayeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.payeeLayout.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = bindingAdapterPosition

                if (previousPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousPosition)
                }
//
                notifyItemChanged(selectedPosition)

                onClick?.invoke(list[selectedPosition].name)
            }
        }

        fun bind() {
            val payee = list[bindingAdapterPosition]
            binding.nameTextView.text = payee.name
            val firstLetter = payee.name.first().toString()

            // Creating a simple drawable background with a letter
            val drawable = TextDrawable(firstLetter)
            binding.letterImageView.setBackgroundColor(Color.BLACK)
            binding.letterImageView.setImageDrawable(drawable)
        }
    }
}