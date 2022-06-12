package com.nonbinsys.greenersapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nonbinsys.greenersapp.comercio.Comercio
import com.nonbinsys.greenersapp.comercio.ComercioList
import com.nonbinsys.greenersapp.data.retrofit.RetrofitInstance
import com.nonbinsys.greenersapp.utils.RequestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuPrincipalViewModel: ViewModel() {
    private var comerciosLiveData = MutableLiveData<List<Comercio>>()

    fun getComercios(){
        RetrofitInstance.api.getComercios().enqueue(object :
            Callback<RequestResponse<ComercioList>> {
            override fun onResponse(
                call: Call<RequestResponse<ComercioList>>,
                response: Response<RequestResponse<ComercioList>>
            ) {
                response.body()?._embedded?.comercioList?.let { comercioList ->
                    comerciosLiveData.postValue(comercioList)
//                    comercioList.forEach { comercio ->
//                        Log.d("APIFunciona", comercio.toString())
//                    }
                }
            }

            override fun onFailure(call: Call<RequestResponse<ComercioList>>, t: Throwable) {
                Log.d("MenuPrincipalErr", t.toString())
            }

        })
    }

    fun encontrarComerciosporNombre(nombreComercio: String){
        RetrofitInstance.api.encontrarComerciosporNombre(nombreComercio).enqueue(object: Callback<RequestResponse<ComercioList>> {
            override fun onResponse(
                call: Call<RequestResponse<ComercioList>>,
                response: Response<RequestResponse<ComercioList>>
            ) {
                response.body()?._embedded?.comercioList?.let {comercioList ->
                    if (comercioList.isEmpty())
                        getComercios()
                    else
                    comerciosLiveData.postValue(comercioList)
                }
                // TODO: CONTROLAR SI LA LISTA VIENE VACIA
            }

            override fun onFailure(call: Call<RequestResponse<ComercioList>>, t: Throwable) {
                Log.d("MenuPrincipalErr", t.toString())
            }

        })

    }

    fun observeComerciosLiveData(): LiveData<List<Comercio>> {
        return comerciosLiveData
    }
}