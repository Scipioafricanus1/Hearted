package com.example.android.hearted

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FavoritesRecyclerView(val list: ArrayList<List> ): RecyclerView.Adapter<FavoritesRecyclerView.ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val inflatedLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder2(inflatedLayout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        val item = list[position]
        holder.textViewName.text = item.item

    }

    class ViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.tv_item)
    }
}