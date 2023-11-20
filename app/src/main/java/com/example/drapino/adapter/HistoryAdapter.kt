package com.example.drapino.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.drapino.databinding.ActivityMainBinding
import com.example.drapino.databinding.HistoryItemBinding
import com.example.drapino.entity.HistoryEntity

class HistoryAdapter()
    : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>()
{
    inner class HistoryViewHolder(private val binding:HistoryItemBinding ):RecyclerView.ViewHolder(binding.root){
        fun bind(historyItem:HistoryEntity){
            binding.storeName.text = historyItem.storeName
            binding.date.text = historyItem.date
            binding.price .text= historyItem.price.toString()
        }


    }
    private val differCallback = object : DiffUtil.ItemCallback<HistoryEntity>(){
        override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem.storeName == newItem.storeName &&
                    oldItem.date== newItem.date &&
                    oldItem.price == newItem.price
        }

        override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        differ.currentList[position]?.let { holder.bind(it) }
    }
}