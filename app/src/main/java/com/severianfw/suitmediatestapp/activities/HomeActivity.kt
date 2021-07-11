package com.severianfw.suitmediatestapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.severianfw.suitmediatestapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var etName: EditText = findViewById(R.id.et_name)
        var btnNext: Button = findViewById(R.id.btn_next)

        btnNext.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("NAME", etName.text.toString())
            startActivity(intent)
        }

    }
}