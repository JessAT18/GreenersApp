package com.nonbinsys.greenersapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:GreenersApi by lazy{
        //Para hacer funcionar la API, la ip debe ser la de la computadora(IPv4)
        Retrofit.Builder()
            .baseUrl("http://192.168.0.4:9000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GreenersApi::class.java)
    }
}