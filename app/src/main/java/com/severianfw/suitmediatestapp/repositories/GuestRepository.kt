package com.severianfw.suitmediatestapp.repositories

import com.severianfw.suitmediatestapp.services.GuestService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GuestRepository {

    fun create(): GuestService{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GuestService::class.java)
    }


}