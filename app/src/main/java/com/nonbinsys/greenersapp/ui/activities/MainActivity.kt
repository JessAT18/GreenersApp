package com.nonbinsys.greenersapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nonbinsys.greenersapp.R
import com.nonbinsys.greenersapp.data.pojo.ComercioList
import com.nonbinsys.greenersapp.data.retrofit.RetrofitInstance
import com.nonbinsys.greenersapp.databinding.ActivityMainBinding
import com.nonbinsys.greenersapp.utils.RequestResponse
import com.nonbinsys.greenersapp.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //Configurations
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    fun getComercios(){
        RetrofitInstance.api.getComercios().enqueue(object : Callback<RequestResponse<ComercioList>>{
            override fun onResponse(
                call: Call<RequestResponse<ComercioList>>,
                response: Response<RequestResponse<ComercioList>>
            ) {
                response.body()?._embedded?.comercioList?.let { comercioList ->
                    comercioList.forEach { comercio ->
                        Log.d("APIFunciona", comercio.toString())
                    }
                }
            }

            override fun onFailure(call: Call<RequestResponse<ComercioList>>, t: Throwable) {
                Log.d("MainActivityErr", t.toString())
            }

        })
    }

    fun onIniciarSesionClick(view: View) {
    val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}