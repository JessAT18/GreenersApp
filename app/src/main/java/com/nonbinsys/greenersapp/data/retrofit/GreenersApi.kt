package com.nonbinsys.greenersapp.data.retrofit

import com.nonbinsys.greenersapp.data.pojo.ComercioList
import com.nonbinsys.greenersapp.utils.RequestResponse
import retrofit2.Call
import retrofit2.http.GET

interface GreenersApi {
    @GET("comercios/listarComercios")
    fun getComercios(): Call<RequestResponse<ComercioList>>
}