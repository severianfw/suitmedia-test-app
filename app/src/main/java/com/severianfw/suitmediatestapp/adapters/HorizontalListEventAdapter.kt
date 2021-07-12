package com.severianfw.suitmediatestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.models.Event

class HorizontalListEventAdapter(private val listEvent: ArrayList<Event>) : RecyclerView.Adapter<HorizontalListEventAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNameH: TextView = itemView.findViewById(R.id.tv_event_name_h)
        var imgEventH: ImageView = itemView.findViewById(R.id.iv_event_h)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_event, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val event = listEvent[position]

        holder.tvNameH.text = event.name
        Glide.with(holder.itemView.context)
            .load(event.img)
            .into(holder.imgEventH)
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

}