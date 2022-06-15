package com.nonbinsys.greenersapp.paquete

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "carritoInformation")
data class PaqueteCarrito(
    @PrimaryKey
    var id: Long,
    var codigo: String?,
    var precio: Float?,
    var nombre: String?,
    var cantidad: Long?
)
