package com.severianfw.suitmediatestapp.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.adapters.ListEventAdapter
import com.severianfw.suitmediatestapp.models.Event
import com.severianfw.suitmediatestapp.repositories.EventRepository

class EventActivity : AppCompatActivity() {
    private lateinit var rvEvents: RecyclerView
    private var listEvent: ArrayList<Event> = arrayListOf()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        rvEvents = findViewById(R.id.rv_event)
        listEvent.addAll(EventRepository.listData)
        val listEventAdapter = ListEventAdapter(listEvent)
        rvEvents.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@EventActivity)
            adapter = listEventAdapter
        }

        listEventAdapter.setOnItemClickCallback(object : ListEventAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Event) {
                saveEventName(data.name)
                finish()
            }
        })

    }

    fun saveEventName(name: String) {
        sharedPreferences = getSharedPreferences("EVENT", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString("NAME", name)
        editor.apply()
    }
}