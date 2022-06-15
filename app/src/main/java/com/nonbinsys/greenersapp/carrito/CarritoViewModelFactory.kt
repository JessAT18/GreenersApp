package com.nonbinsys.greenersapp.carrito

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nonbinsys.greenersapp.database.PaqueteDatabase

class CarritoViewModelFactory(
    private val paqueteDatabase: PaqueteDatabase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarritoViewModel(paqueteDatabase) as T
    }
}