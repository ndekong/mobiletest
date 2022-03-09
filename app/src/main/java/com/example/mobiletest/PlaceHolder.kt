package com.example.mobiletest

import retrofit2.Call
import retrofit2.http.GET

interface PlaceHolder {

    @GET(" rides")
    fun getData(): Call<List<Rides>>
}