package com.pmacademy.razvii_pt13

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerViewAdapter(var listItem: List<SpannableStringBuilder>) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        holder.itemTextView.text = listItem[position]

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class myViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.tvTextRV)

    }
}