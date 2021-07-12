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

    private val eventLat = doubleArrayOf(-7.7837274,
        -7.7833447,
        -7.7831746,
        -7.7831321
    )

    private val eventLong = doubleArrayOf(110.4015465,
        110.4142924,
        110.4057093,
        110.3960963
    )

    val listData: ArrayList<Event>
        get() {
            val list = arrayListOf<Event>()
            for (position in eventNames.indices) {
                val event = Event()
                event.name = eventNames[position]
                event.date = eventDate[position]
                event.img = eventImg[position]
                event.lat = eventLat[position]
                event.long = eventLong[position]
                list.add(event)
            }
            return list
        }

}