package com.example.wowlucky.screens.game.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wowlucky.R
import com.example.wowlucky.databinding.GameItemBinding
import com.example.wowlucky.screens.game.interfaces.GameItemClick

class GameAdapter(private val listener: GameItemClick) : ListAdapter<GameItem, GameAdapter.GameViewHolder>(
    GameDiffCallback()
) {
    private var selectedPosition: Int = -1
    private var isTextVisible = false

    inner class GameViewHolder(private val binding: GameItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gameItem: GameItem) {
            binding.ivGun.setImageResource(gameItem.imageResource)
            if (position == selectedPosition) {
                binding.frame1.setBackgroundResource(R.drawable.select_game)
            } else {
                binding.frame1.setBackgroundResource(R.drawable.game)
            }
            binding.tvPlayer1.visibility = if (isTextVisible) View.VISIBLE else View.GONE

            binding.root.setOnClickListener {
                listener.onItemClick()
                val previousSelectedPosition = selectedPosition
                selectedPosition = position
                isTextVisible = !isTextVisible
                notifyDataSetChanged()
                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}


class GameDiffCallback : DiffUtil.ItemCallback<GameItem>() {
    override fun areItemsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
        return oldItem == newItem
    }
}

class GameItem(val imageResource: Int)