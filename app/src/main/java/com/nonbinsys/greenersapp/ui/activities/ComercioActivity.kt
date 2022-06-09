package com.nonbinsys.greenersapp.ui.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.R
import com.nonbinsys.greenersapp.adapters.PaquetesAdapter
import com.nonbinsys.greenersapp.data.pojo.Comercio
import com.nonbinsys.greenersapp.databinding.ActivityComercioBinding
import com.nonbinsys.greenersapp.viewmodel.ComercioViewModel

class ComercioActivity : AppCompatActivity() {
    /**DATOS DEL COMERCIO*/
    private lateinit var comercioId: String
    private lateinit var comercioNombre: String
    private lateinit var comercioDireccion: String
    private lateinit var comercioTelf: String
    private lateinit var comercioLinkLogo: String

    lateinit var binding: ActivityComercioBinding
    lateinit var comercioViewModel: ComercioViewModel
    lateinit var paquetesAdapter: PaquetesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComercioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getComercioInformationFromIntent()

        setInformationComercio()
//        prepararPaquetesRecyclerView()
    }

    private fun getComercioInformationFromIntent() {
        comercioId = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_ID)!!
        comercioNombre = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_NOMBRE)!!
        comercioDireccion = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_DIRECCION)!!
        comercioTelf = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_TELEFONO)!!
        comercioLinkLogo = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_LINK_LOGO)!!
    }

    private fun setInformationComercio() {
        //Toolbar

        binding.ctbMain.title = comercioNombre

        //Detalles del comercio

        Glide.with(applicationContext)
            .load(comercioLinkLogo)
            .into(binding.imgComercio)

        binding.tvNombreComercio.text = comercioNombre
        binding.tvDireccion.text = "Dirección: " + comercioDireccion
        binding.tvTelefono.text = "Teléfono: " + comercioTelf

    }
//
//    private fun prepararPaquetesRecyclerView() {
//        return 0
//    }


}