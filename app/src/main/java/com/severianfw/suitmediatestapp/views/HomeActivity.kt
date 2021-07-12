package com.severianfw.suitmediatestapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.severianfw.suitmediatestapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val etName: EditText = findViewById(R.id.et_name)
        val btnNext: Button = findViewById(R.id.btn_next)


        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            var name: String = etName.text.toString()
            intent.putExtra("NAME", name)
            if (isPalindrome(name)){
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
            }
            startActivity(intent)
        }

    }

    private fun isPalindrome(name: String): Boolean{
        var reverseString: String = name.reversed()
        // removing whitespace
        reverseString = reverseString.replace("\\s".toRegex(), "")
        val nameString = name.replace("\\s".toRegex(), "")

        if (reverseString == nameString){
            return true;
        }
        return false
    }
}