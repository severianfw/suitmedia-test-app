package com.severianfw.suitmediatestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.models.Guest

class GridGuestAdapter(private val listGuest: ArrayList<Guest>) : RecyclerView.Adapter<GridGuestAdapter.GridViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_guest_name)
        val img: ImageView = itemView.findViewById(R.id.iv_guest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_guest, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val guest = listGuest[position]

        holder.tvName.text = guest.name
        holder.img.setImageResource(R.drawable.hackathon)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGuest[holder.absoluteAdapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listGuest.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Guest)
    }
}