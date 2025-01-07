package com.example.wowlucky.screens.wallet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wowlucky.databinding.DateNotificationBinding
import com.example.wowlucky.databinding.TransactionItemBinding

class GamingTransactionsAdapter(val listener: TransactionInterface): ListAdapter<Transaction, RecyclerView.ViewHolder>(
    TransactionDiffCallback()
) {
    companion object {
        const val TYPE_GAME = 0
        const val TYPE_DATE1 = 1
    }
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Transaction.GameMessage -> TYPE_GAME
            is Transaction.DateMessage -> TYPE_DATE1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_GAME -> {
                val binding = TransactionItemBinding.inflate(inflater, parent, false)
                GameViewHolder(binding)
            }
            TYPE_DATE1 -> {
                val binding = DateNotificationBinding.inflate(inflater, parent, false)
                DateViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GameViewHolder -> holder.bind((getItem(position) as Transaction.GameMessage).gameNumber, (getItem(position) as Transaction.GameMessage).win,
                (getItem(position) as Transaction.GameMessage).name1, (getItem(position) as Transaction.GameMessage).name2, (getItem(position) as Transaction.GameMessage).name3,
                (getItem(position) as Transaction.GameMessage).coins1, (getItem(position) as Transaction.GameMessage).coins2, (getItem(position) as Transaction.GameMessage).coins3)
            is DateViewHolder -> holder.bind((getItem(position) as Transaction.DateMessage).date)
        }
    }

    inner class GameViewHolder(private val binding: TransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gameNumber: String, win: String, name1: String, name2: String, name3: String, coins1: String, coins2: String, coins3: String) {
            binding.allView.setOnClickListener {
                listener.onButtonClick()
            }
            binding.tvGame.text = gameNumber
            binding.tvWins.text = win
            binding.tvNameUser1.text = name1
            binding.tvNameUser2.text = name2
            binding.tvNameUser3.text = name3
            binding.tvCoins1.text = coins1
            binding.tvCoins2.text = coins2
            binding.tvCoins3.text = coins3
        }
    }

    inner class DateViewHolder(private val binding: DateNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.tvDate.text = message
        }
    }

    class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }
    }
}

sealed class Transaction {
    data class GameMessage(val gameNumber: String, val win: String, val name1: String, val name2: String, val name3: String, val coins1: String, val coins2: String,val coins3: String) : Transaction()
    data class DateMessage(val date: String) : Transaction()
}

interface TransactionInterface {
    fun onButtonClick()
}