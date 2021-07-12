package com.severianfw.suitmediatestapp.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.severianfw.suitmediatestapp.EventMapsFragment
import com.severianfw.suitmediatestapp.MapsFragment
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

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        val btnMap: ImageButton = findViewById(R.id.btn_map)
        val btnSearch: ImageButton = findViewById(R.id.btn_search)

        btnBack.setOnClickListener {
            finish()
        }
        btnSearch.setOnClickListener {
            btnSearch.setImageResource(R.drawable.ic_baseline_search_24_selected)
            btnMap.setImageResource(R.drawable.btn_new_article_normal)
            supportFragmentManager.popBackStackImmediate()
        }
        btnMap.setOnClickListener {
            btnSearch.setImageResource(R.drawable.ic_baseline_search_24)
            btnMap.setImageResource(R.drawable.btn_new_article_selected)
            supportFragmentManager.beginTransaction().addToBackStack("MAPS_FRAGMENT").replace(R.id.frame, MapsFragment()).commit()
        }

        val listEventAdapter = ListEventAdapter(listEvent)
        showListEvents(listEventAdapter)
        listEventAdapter.setOnItemClickCallback(object : ListEventAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Event) {
                saveEventName(data.name)
                finish()
            }
        })
        swipeAction()
    }

    override fun onBackPressed() {
        val btnMap: ImageButton = findViewById(R.id.btn_map)
        val btnSearch: ImageButton = findViewById(R.id.btn_search)
        btnMap.setImageResource(R.drawable.btn_new_article_normal)
        btnSearch.setImageResource(R.drawable.ic_baseline_search_24_selected)
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }

    private fun showListEvents(listEventAdapter: ListEventAdapter) {
        rvEvents = findViewById(R.id.rv_event)
        listEvent.addAll(EventRepository.listData)
        rvEvents.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@EventActivity)
            adapter = listEventAdapter
        }
    }


    private fun swipeAction(){
        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            // Refresh event data
            val listEventAdapter = ListEventAdapter(listEvent)
            rvEvents.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@EventActivity)
                adapter = listEventAdapter
            }
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun saveEventName(name: String) {
        sharedPreferences = getSharedPreferences("EVENT", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString("NAME", name)
        editor.apply()
    }
}