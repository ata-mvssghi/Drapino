package com.example.drapino.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.drapino.databinding.HistoryItemBinding
import com.example.drapino.databinding.StoreHistoryItemBinding
import com.example.drapino.entity.HistoryEntity
import com.example.drapino.entity.StoreItem

class StoreAdapter
    :RecyclerView.Adapter<StoreAdapter.StoreViewHolder>()
{
    inner class StoreViewHolder(private val binding: StoreHistoryItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(store:StoreItem){
            binding.StoreNameHistory.text = store.storeName
            binding.percentage.text = store.percentage.toString()

        }
    }
    private val differCallback = object : DiffUtil.ItemCallback<StoreItem>(){
        override fun areItemsTheSame(oldItem: StoreItem, newItem: StoreItem): Boolean {
            return oldItem.storeName == newItem.storeName &&
                    oldItem.percentage== newItem.percentage
        }

        override fun areContentsTheSame(oldItem: StoreItem, newItem: StoreItem): Boolean {
            return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = StoreHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        differ.currentList[position].also {holder.bind(it)}
    }
}