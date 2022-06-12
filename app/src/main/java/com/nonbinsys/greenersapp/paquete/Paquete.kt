package com.nonbinsys.greenersapp.paquete

import java.math.BigDecimal

data class Paquete(
    val id: Long,
    val codigo: String,
    val precio: BigDecimal,
    val nombre: String,
    val descripcion: String,
    val link_paquete: String
)
