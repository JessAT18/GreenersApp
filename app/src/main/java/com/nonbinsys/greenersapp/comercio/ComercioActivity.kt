package com.nonbinsys.greenersapp.comercio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.paquete.PaquetesAdapter
import com.nonbinsys.greenersapp.databinding.ActivityComercioBinding
import com.nonbinsys.greenersapp.paquete.PaqueteActivity
import com.nonbinsys.greenersapp.ui.activities.MenuPrincipalActivity

class ComercioActivity : AppCompatActivity() {
    /**DATOS DEL COMERCIO*/
    private var comercioId: Long = 0
    private lateinit var comercioNombre: String
    private lateinit var comercioDireccion: String
    private lateinit var comercioTelf: String
    private lateinit var comercioLinkLogo: String

    lateinit var binding: ActivityComercioBinding
    lateinit var comercioViewModel: ComercioViewModel
    lateinit var paquetesAdapter: PaquetesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityComercioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comercioViewModel = ViewModelProvider(this)[ComercioViewModel::class.java]

        getComercioInformationFromIntent()
        setInformationComercio()

        prepararPaquetesRecyclerView()
        comercioViewModel.encontrarPaquetesPorComercio(comercioId)
        observerPaquetesLiveData()
        
        onPaqueteClick()
    }

    private fun onPaqueteClick() {
        paquetesAdapter.onItemClick = { paquete ->
            val intent = Intent(this, PaqueteActivity::class.java)
            //PUT EXTRA
            startActivity(intent)
        }
    }

    private fun observerPaquetesLiveData() {
        comercioViewModel.observePaquetesLiveData().observe(this, Observer { paquetes ->
            paquetesAdapter.setPaquetesList(paquetes)
        })
    }

    private fun prepararPaquetesRecyclerView() {
        paquetesAdapter = PaquetesAdapter()
        binding.rvPaquetes.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = paquetesAdapter
        }
    }

    private fun getComercioInformationFromIntent() {
        comercioId = intent.getLongExtra(MenuPrincipalActivity.COMERCIO_ID, 0)!!
        comercioNombre = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_NOMBRE)!!
        comercioDireccion = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_DIRECCION)!!
        comercioTelf = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_TELEFONO)!!
        comercioLinkLogo = intent.getStringExtra(MenuPrincipalActivity.COMERCIO_LINK_LOGO)!!
    }

    private fun setInformationComercio() {
        //Detalles del comercio

        Glide.with(applicationContext)
            .load(comercioLinkLogo)
            .into(binding.imgComercio)

        binding.tvNombreComercio.text = comercioNombre
        binding.tvDireccion.text = "Dirección: " + comercioDireccion
        binding.tvTelefono.text = "Teléfono: " + comercioTelf

    }

}