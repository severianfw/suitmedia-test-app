package com.severianfw.suitmediatestapp.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.adapters.GridGuestAdapter
import com.severianfw.suitmediatestapp.models.Guest
import com.severianfw.suitmediatestapp.repositories.GuestRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Month

class GuestActivity : AppCompatActivity() {
    private lateinit var rvGuest: RecyclerView
    private var listGuest: List<Guest> = arrayListOf()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        val guestService = GuestRepository.create()
        rvGuest = findViewById(R.id.rv_guest)

        guestService.getGuests().enqueue(object : Callback<List<Guest>> {
            override fun onResponse(call: Call<List<Guest>>, response: Response<List<Guest>>) {
                val guest = response.body()
                listGuest = guest!!
                var gridGuestAdapter = GridGuestAdapter(listGuest as ArrayList<Guest>)
                showGuestList(gridGuestAdapter)

                gridGuestAdapter.setOnItemClickCallback(object : GridGuestAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Guest) {
                        saveGuestName(data.name)
                        dateCheck(data)
                        if (isPrimeMonth(data.birthdate)) {
                            Toast.makeText(this@GuestActivity, "is a Prime Month!", Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
                })
            }
            override fun onFailure(call: Call<List<Guest>>, t: Throwable) {
                Toast.makeText(this@GuestActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun showGuestList(gridGuestAdapter: GridGuestAdapter) {
        rvGuest.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@GuestActivity, 2)
            adapter = gridGuestAdapter
        }
    }

    private fun isPrimeMonth(date: String): Boolean{
        var month: String = buildString {
            append(date[5])
            append(date[6])
        }
        var num = month.toInt()

        if (num > 2){
            for (i in 2 until num) {
                if (num % i == 0){
                    return false
                }
            }
        }
        return true
    }

    private fun dateCheck(data: Guest) {
        var date: String = data.birthdate
        var num: String = buildString {
            append(date[date.length-1])
            append(date[date.lastIndex])
        }

        if (num.toInt() % 2 == 0 && num.toInt() % 3 == 0) {
            Toast.makeText(this@GuestActivity, "iOS", Toast.LENGTH_SHORT).show()
        } else if (num.toInt() % 2 == 0) {
            Toast.makeText(this@GuestActivity, "blackberry", Toast.LENGTH_SHORT).show()
        } else if (num.toInt() % 3 == 0){
            Toast.makeText(this@GuestActivity, "android", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@GuestActivity, "feature phone", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveGuestName(name: String) {
        sharedPreferences = getSharedPreferences("GUEST", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("NAME", name)
        editor.apply()
    }
}
