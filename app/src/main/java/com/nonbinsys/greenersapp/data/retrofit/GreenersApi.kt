package com.nonbinsys.greenersapp.data.retrofit

import com.nonbinsys.greenersapp.comercio.ComercioList
import com.nonbinsys.greenersapp.utils.RequestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GreenersApi {
    @GET("comercios/listarComercios")
    fun getComercios(): Call<RequestResponse<ComercioList>>
    @GET("comercios/encontrarComerciosPorNombre/{nombre}")
    fun encontrarComerciosporNombre(@Path("nombre") nombreComercio: String): Call<RequestResponse<ComercioList>>
}