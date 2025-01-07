package com.example.wowlucky.screens.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wowlucky.databinding.ViewPagerNewsBinding

data class News(
    val title: String,
    val description: String
)

class NewsAdapter(private val listener: NewsViewPagerAdapterClickItem) : ListAdapter<News, NewsAdapter.NewsViewHolder>(
    NewsDiffCallback()
) {

    class NewsViewHolder(private val binding: ViewPagerNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News, listener: NewsViewPagerAdapterClickItem) {
            binding.tvNews.text = news.title
            binding.tvLorem.text = news.description
            binding.constraintLayout.setOnClickListener {
                listener.onButtonClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewPagerNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem, listener)
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}

interface NewsViewPagerAdapterClickItem {
    fun onButtonClick()
}
