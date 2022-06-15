package com.nonbinsys.greenersapp.comercio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nonbinsys.greenersapp.data.retrofit.RetrofitInstance
import com.nonbinsys.greenersapp.paquete.PaqueteInventario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComercioViewModel: ViewModel() {
    private var paquetesInventarioLiveData = MutableLiveData<List<PaqueteInventario>>()

    fun encontrarPaquetesPorComercio(id_comercio: Long){
        RetrofitInstance.api.encontrarInventarioPorComercio(id_comercio).enqueue(object:
            Callback<List<PaqueteInventario>> {
            override fun onResponse(call: Call<List<PaqueteInventario>>, response: Response<List<PaqueteInventario>>) {
                response.body()?.let { paqueteList ->
                    paquetesInventarioLiveData.postValue(paqueteList)
                }
            }

            override fun onFailure(call: Call<List<PaqueteInventario>>, t: Throwable) {
                Log.d("ComercioVM", t.toString())
            }

        })
    }

    fun observePaquetesInventarioLiveData(): LiveData<List<PaqueteInventario>> {
        return paquetesInventarioLiveData
    }
}