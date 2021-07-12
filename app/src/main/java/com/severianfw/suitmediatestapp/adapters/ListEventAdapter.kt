package com.severianfw.suitmediatestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.models.Event

class ListEventAdapter(private val listEvent: ArrayList<Event>) : RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_event_name)
        var tvDate: TextView = itemView.findViewById(R.id.tv_event_date)
        var imgEvent: ImageView = itemView.findViewById(R.id.iv_event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_event, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val event = listEvent[position]

        holder.tvName.text = event.name
        holder.tvDate.text = event.date
        Glide.with(holder.itemView.context)
            .load(event.img)
            .apply(RequestOptions().override(75, 75))
            .into(holder.imgEvent)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listEvent[holder.absoluteAdapterPosition])}
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Event)
    }
}