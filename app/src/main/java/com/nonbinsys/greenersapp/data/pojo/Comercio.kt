package com.nonbinsys.greenersapp.data.pojo

data class Comercio(
    val id: Long,
    val nombre: String,
    val telf: String,
    val direccion: String,
    val link_logo: String,

    val id_adm_comercio: Long
)
