package com.severianfw.suitmediatestapp.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.severianfw.suitmediatestapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var eventData: SharedPreferences
    private lateinit var guestData: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name: String = intent.getStringExtra("NAME").toString()
        val tvName: TextView = findViewById(R.id.tv_name)
        tvName.text = name

        val btnEvent: Button = findViewById(R.id.btn_event)
        val btnGuest: Button = findViewById(R.id.btn_guest)

        btnEvent.setOnClickListener {
            startActivity(Intent(this, EventActivity::class.java))
        }
        btnGuest.setOnClickListener {
            startActivity(Intent(this, GuestActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        changeBtnEventText()
        changeBtnGuestText()
    }

    override fun onStop() {
        super.onStop()
        clearData()
    }

    private fun changeBtnEventText() {
        val btnEvent: Button = findViewById(R.id.btn_event)
        eventData = getSharedPreferences("EVENT", MODE_PRIVATE)
        val eventName: String? = eventData.getString("NAME", "")
        if (eventName != ""){
            btnEvent.text = eventName
        }
    }

    private fun changeBtnGuestText() {
        val btnGuest: Button = findViewById(R.id.btn_guest)
        guestData = getSharedPreferences("GUEST", MODE_PRIVATE)
        val guestName: String? = guestData.getString("NAME", "")
        if (guestName != ""){
            btnGuest.text = guestName
        }
    }

    private fun clearData(){
        eventData = getSharedPreferences("EVENT", MODE_PRIVATE)
        var eventEditor: SharedPreferences.Editor = eventData.edit()
        eventEditor.clear()
        eventEditor.apply()

        guestData = getSharedPreferences("GUEST", MODE_PRIVATE)
        var guestEditor: SharedPreferences.Editor = guestData.edit()
        guestEditor.clear()
        guestEditor.apply()
    }

}