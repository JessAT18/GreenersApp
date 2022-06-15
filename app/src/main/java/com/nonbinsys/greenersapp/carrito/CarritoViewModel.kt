package com.nonbinsys.greenersapp.carrito

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nonbinsys.greenersapp.database.PaqueteDatabase
import com.nonbinsys.greenersapp.paquete.PaqueteCarrito

class CarritoViewModel(
    val paqueteDatabase: PaqueteDatabase
    ): ViewModel() {
    private var carritoLiveData = MutableLiveData<List<PaqueteCarrito>>()
    var carrito2LiveData = paqueteDatabase.paqueteDao().getAllPaquetes()

    fun observeCarritoLiveData(): LiveData<List<PaqueteCarrito>> {
        return carrito2LiveData
    }
}