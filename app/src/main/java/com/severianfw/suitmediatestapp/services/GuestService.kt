package com.severianfw.suitmediatestapp.services

import com.severianfw.suitmediatestapp.models.Guest
import retrofit2.http.GET
import retrofit2.Call

interface GuestService {

    @GET("v2/596dec7f0f000023032b8017")
    fun getGuests(): Call<List<Guest>>
}