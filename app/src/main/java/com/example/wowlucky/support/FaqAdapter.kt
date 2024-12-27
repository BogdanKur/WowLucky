package com.example.wowlucky.support

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FaqItemBinding

class FaqAdapter : ListAdapter<FaqItem, FaqAdapter.FaqViewHolder>(FaqDiffCallback()) {

    inner class FaqViewHolder(private val binding: FaqItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(faqItem: FaqItem) {
            binding.root.setOnClickListener {
                if (binding.questionAnswer1.visibility == View.GONE) {
                    binding.clQuestionOne.setBackgroundResource(R.drawable.faq_bg1)
                    binding.questionAnswer1.visibility = View.VISIBLE
                    binding.openQuestionOne.setImageResource(R.drawable.down_white)
                } else {
                    binding.clQuestionOne.setBackgroundResource(R.drawable.faq_bg)
                    binding.questionAnswer1.visibility = View.GONE
                    binding.openQuestionOne.setImageResource(R.drawable.up_white)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val binding = FaqItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}


class FaqDiffCallback : DiffUtil.ItemCallback<FaqItem>() {
    override fun areItemsTheSame(oldItem: FaqItem, newItem: FaqItem): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: FaqItem, newItem: FaqItem): Boolean {
        return oldItem == newItem
    }
}

class FaqItem