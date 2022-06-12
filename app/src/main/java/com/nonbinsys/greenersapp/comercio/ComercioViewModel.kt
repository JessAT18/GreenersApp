package com.nonbinsys.greenersapp.comercio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nonbinsys.greenersapp.data.retrofit.RetrofitInstance
import com.nonbinsys.greenersapp.paquete.Paquete
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComercioViewModel: ViewModel() {
    private var paquetesLiveData = MutableLiveData<List<Paquete>>()

    fun encontrarPaquetesPorComercio(id_comercio: Long){
        RetrofitInstance.api.encontrarInventarioPorComercio(id_comercio).enqueue(object:
            Callback<List<Paquete>> {
            override fun onResponse(call: Call<List<Paquete>>, response: Response<List<Paquete>>) {
                response.body()?.let { paqueteList ->
                    paquetesLiveData.postValue(paqueteList)
                }
            }

            override fun onFailure(call: Call<List<Paquete>>, t: Throwable) {
                Log.d("ComercioVM", t.toString())
            }

        })
    }

    fun observePaquetesLiveData(): LiveData<List<Paquete>> {
        return paquetesLiveData
    }
}