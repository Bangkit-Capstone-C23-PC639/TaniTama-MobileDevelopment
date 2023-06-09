package com.tanitama.green.data.repository.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tanitama.green.data.model.response.history.Data
import com.tanitama.green.databinding.ItemHistoryDetectionBinding

class HistoryAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val itemList = mutableListOf<Data>()

    inner class HistoryViewHolder(private val binding: ItemHistoryDetectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.apply {
                tvDisease.text = item.result.name
                Glide.with(root)
                    .load(item.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivDisease)
                root.setOnClickListener {
                    itemClickListener.onItemUserListClicked(item.result.id, item.imageUrl, item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemHistoryDetectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        itemList[position].let { holder.bind(it) }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Data>) {
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemUserListClicked(id: Int, imageLink: String, idHistory: Int)
    }


}