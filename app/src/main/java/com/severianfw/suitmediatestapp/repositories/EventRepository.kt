package com.severianfw.suitmediatestapp.repositories

import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.models.Event

object EventRepository {
    private val eventNames = arrayOf("Technoscape",
    "Compfest",
    "BigBang",
    "Hackathon")

    private val eventDate = arrayOf("11 Agustus 2021",
    "22 September 2021",
    "10 Oktober 2021",
    "20 Desember 2021")

    private val eventImg = intArrayOf(R.drawable.technoscape,
        R.drawable.compfest,
        R.drawable.bigbang,
        R.drawable.hackathon
    )

    val listData: ArrayList<Event>
        get() {
            val list = arrayListOf<Event>()
            for (position in eventNames.indices) {
                val event = Event()
                event.name = eventNames[position]
                event.date = eventDate[position]
                event.img = eventImg[position]
                list.add(event)
            }
            return list
        }

}