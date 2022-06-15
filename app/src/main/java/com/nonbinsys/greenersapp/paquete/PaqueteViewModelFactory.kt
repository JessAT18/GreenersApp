package com.nonbinsys.greenersapp.paquete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nonbinsys.greenersapp.database.PaqueteDatabase

class PaqueteViewModelFactory(
    private val paqueteDatabase: PaqueteDatabase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaqueteViewModel(paqueteDatabase) as T
    }
}