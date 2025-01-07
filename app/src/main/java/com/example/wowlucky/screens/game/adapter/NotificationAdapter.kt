package com.example.wowlucky.screens.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wowlucky.databinding.DateNotificationBinding
import com.example.wowlucky.databinding.LifeItemBinding
import com.example.wowlucky.databinding.WinBinding
import com.example.wowlucky.databinding.WithdrawItemBinding

class NotificationAdapter: ListAdapter<Message, RecyclerView.ViewHolder>(NotificationDiffCallback()) {
    companion object {
        const val TYPE_WIN = 0
        const val TYPE_LIFE = 1
        const val TYPE_WITHDRAW = 2
        const val TYPE_DATE = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Message.WinMessage -> TYPE_WIN
            is Message.WithdrawMessage -> TYPE_WITHDRAW
            is Message.LifeMessage -> TYPE_LIFE
            is Message.DateMessage -> TYPE_DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_WIN -> {
                val binding = WinBinding.inflate(inflater, parent, false)
                WinViewHolder(binding)
            }
            TYPE_LIFE -> {
                val binding = LifeItemBinding.inflate(inflater, parent, false)
                LifeViewHolder(binding)
            }
            TYPE_WITHDRAW -> {
                val binding = WithdrawItemBinding.inflate(inflater, parent, false)
                WithdrawViewHolder(binding)
            }
            TYPE_DATE -> {
                val binding = DateNotificationBinding.inflate(inflater, parent, false)
                DateSupportViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WinViewHolder -> holder.bind((getItem(position) as Message.WinMessage).message)
            is LifeViewHolder -> holder.bind((getItem(position) as Message.LifeMessage).message)
            is WithdrawViewHolder -> holder.bind((getItem(position) as Message.WithdrawMessage).message)
            is DateSupportViewHolder -> holder.bind((getItem(position) as Message.DateMessage).date)
        }
    }

    inner class WinViewHolder(private val binding: WinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvStars.text = message
        }
    }

    inner class LifeViewHolder(private val binding: LifeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvLife.text = message
        }
    }
    inner class WithdrawViewHolder(private val binding: WithdrawItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvStar.text = message
        }
    }

    inner class DateSupportViewHolder(private val binding: DateNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvDate.text = message
        }
    }

    class NotificationDiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }

}

sealed class Message {
    data class WinMessage(val message: String) : Message()
    data class LifeMessage(val message: String) : Message()
    data class WithdrawMessage(val message: String) : Message()
    data class DateMessage(val date: String) : Message()
}