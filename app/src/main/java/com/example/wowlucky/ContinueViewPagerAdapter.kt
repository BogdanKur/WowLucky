package com.example.wowlucky

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wowlucky.databinding.ViewPagerBinding

class ContinueViewPagerAdapter : ListAdapter<Continue, ContinueViewPagerAdapter.ContinueViewPagerViewHolder>(ViewPagerDiffCallback()) {
    class ContinueViewPagerViewHolder(private val binding: ViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Continue) {
            binding.root.setBackgroundResource(item.imageResId1)
            binding.ivSecond1.setImageResource(item.imageResId2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinueViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewPagerBinding.inflate(inflater, parent, false)
        return ContinueViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContinueViewPagerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewPagerDiffCallback : DiffUtil.ItemCallback<Continue>() {
        override fun areItemsTheSame(oldItem: Continue, newItem: Continue): Boolean {
            return oldItem.imageResId1 == newItem.imageResId1
        }

        override fun areContentsTheSame(oldItem: Continue, newItem: Continue): Boolean {
            return oldItem == newItem
        }
    }
}

data class Continue(
    val imageResId1: Int,
    val imageResId2: Int
)