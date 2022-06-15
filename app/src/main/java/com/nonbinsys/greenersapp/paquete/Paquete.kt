package com.nonbinsys.greenersapp.paquete

data class Paquete(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val link_paquete: String,
    val habilitado: Boolean,
    val id_comercio: Long,
    val id_tipo_paquete: Long
)
