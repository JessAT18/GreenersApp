package com.nonbinsys.greenersapp.paquete

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonbinsys.greenersapp.data.retrofit.RetrofitInstance
import com.nonbinsys.greenersapp.database.PaqueteDatabase
import com.nonbinsys.greenersapp.utils.RequestResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaqueteViewModel(
    val paqueteDatabase: PaqueteDatabase
): ViewModel() {
    private var paqueteLiveData = MutableLiveData<Paquete>()

    fun getPaquete(id: Long) {
        RetrofitInstance.api.getPaquete(id).enqueue(object: Callback<Paquete> {
            override fun onResponse(call: Call<Paquete>, response: Response<Paquete>) {
                response.body()?.let { paquete ->
                    paqueteLiveData.postValue(paquete)
                }
            }

            override fun onFailure(call: Call<Paquete>, t: Throwable) {
                Log.d("PaqueteViewModel", t.toString())
            }
        })
    }
    fun observePaqueteLiveData(): LiveData<Paquete> {
        return paqueteLiveData
    }

    fun insertPaquete(paquete: PaqueteCarrito)
    {
        viewModelScope.launch {
            paqueteDatabase.paqueteDao().upsert(paquete)
            Log.d("PAQUETE", "GUARDO")
        }
    }
}