package com.example.wowlucky.screens.support.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wowlucky.databinding.BotMessageBinding
import com.example.wowlucky.databinding.DateNotificationBinding
import com.example.wowlucky.databinding.YourMessageBinding

class MessageAdapter : ListAdapter<Messages, RecyclerView.ViewHolder>(MessageDiffCallback()) {

    companion object {
        const val TYPE_BOT = 0
        const val TYPE_USER = 1
        const val TYPE_DATE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Messages.BotMessage -> TYPE_BOT
            is Messages.YourMessage -> TYPE_USER
            is Messages.DateMessage -> TYPE_DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_BOT -> {
                val binding = BotMessageBinding.inflate(inflater, parent, false)
                BotMessageViewHolder(binding)
            }
            TYPE_USER -> {
                val binding = YourMessageBinding.inflate(inflater, parent, false)
                YourMessageViewHolder(binding)
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
            is BotMessageViewHolder -> holder.bind((getItem(position) as Messages.BotMessage).message)
            is YourMessageViewHolder -> holder.bind((getItem(position) as Messages.YourMessage).message)
            is DateSupportViewHolder -> holder.bind((getItem(position) as Messages.DateMessage).date)
        }
    }

    inner class BotMessageViewHolder(private val binding: BotMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvMessage.text = message
        }
    }

    inner class YourMessageViewHolder(private val binding: YourMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvMessage.text = message
        }
    }
    inner class DateSupportViewHolder(private val binding: DateNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvDate.text = message
        }
    }
}

class MessageDiffCallback : DiffUtil.ItemCallback<Messages>() {
    override fun areItemsTheSame(oldItem: Messages, newItem: Messages): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Messages, newItem: Messages): Boolean {
        return oldItem == newItem
    }
}


sealed class Messages {
    data class BotMessage(val message: String) : Messages()
    data class YourMessage(val message: String) : Messages()
    data class DateMessage(val date: String) : Messages()
}