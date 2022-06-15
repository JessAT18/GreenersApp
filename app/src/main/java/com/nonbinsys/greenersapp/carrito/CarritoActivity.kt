package com.nonbinsys.greenersapp.carrito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nonbinsys.greenersapp.database.PaqueteDatabase
import com.nonbinsys.greenersapp.databinding.ActivityCarritoBinding
import com.nonbinsys.greenersapp.paquete.PaqueteViewModelFactory

class CarritoActivity : AppCompatActivity() {
    private lateinit var viewModel: CarritoViewModel
    private lateinit var carritoAdapter: CarritoAdapter
    private lateinit var binding: ActivityCarritoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paqueteDatabase = PaqueteDatabase.getInstance(this)
        val viewModelFactory = CarritoViewModelFactory(paqueteDatabase)

        viewModel = ViewModelProvider(this, viewModelFactory)[CarritoViewModel::class.java]

        prepararCarritoRecyclerView()

        onBorrarCarritoClick()

        observerCarritoLiveData()
    }

    private fun onBorrarCarritoClick() {
        binding.btnEliminarCarrito.setOnClickListener {
            Toast.makeText(this, "Carrito eliminado", Toast.LENGTH_LONG).show()
        }
    }

    private fun prepararCarritoRecyclerView() {
        carritoAdapter = CarritoAdapter()
        binding.rvCarrito.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = carritoAdapter
        }
    }

    private fun observerCarritoLiveData() {
        viewModel.observeCarritoLiveData().observe(this, Observer { carrito ->
            carritoAdapter.setCarritosList(carrito)
        })
    }
}