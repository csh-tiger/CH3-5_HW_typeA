package com.example.ch3_5hw_api_typea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch3_5hw_api_typea.databinding.ItemRecyclerViewGridBinding

class Adapter(val mItems: MutableList<DataItem>) : RecyclerView.Adapter<Adapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerViewGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        Glide.with(holder.itemView.context)
            .load(mItems[position].thumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
//            .circleCrop()
            .into(holder.itemImage)
        holder.itemTitle.text = mItems[position].siteName
        holder.itemDate.text = mItems[position].datetime
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemRecyclerViewGridBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImage = binding.itemImage
        val itemTitle = binding.itemTitle
        val itemDate = binding.itemDate
        val itemFavorite=binding.itemFavorite
    }
}