package com.nonbinsys.greenersapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nonbinsys.greenersapp.paquete.PaqueteCarrito

@Dao
interface PaqueteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(paquete: PaqueteCarrito)

    @Delete
    suspend fun delete (paquete: PaqueteCarrito)

    @Query("SELECT * FROM carritoInformation")
    fun getAllPaquetes(): LiveData<List<PaqueteCarrito>>
}