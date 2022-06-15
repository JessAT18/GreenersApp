package com.nonbinsys.greenersapp.comercio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.paquete.PaquetesInventarioAdapter
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
    lateinit var paquetesInventarioAdapter: PaquetesInventarioAdapter

    companion object{
        const val PAQUETE_ID = "com.nonbinsys.greenersapp.comercio.paqueteId"
        const val PAQUETE_NOMBRE = "com.nonbinsys.greenersapp.comercio.paqueteNombre"
        const val PAQUETE_DESCRIPCION = "com.nonbinsys.greenersapp.comercio.paqueteDescripcion"
        const val PAQUETE_LINK_PAQUETE = "com.nonbinsys.greenersapp.comercio.paqueteLinkPaquete"
        const val PAQUETE_CODIGO = "com.nonbinsys.greenersapp.comercio.paqueteCodigo"
        const val PAQUETE_PRECIO = "com.nonbinsys.greenersapp.comercio.paquetePrecio"
    }

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
        paquetesInventarioAdapter.onItemClick = { paquete ->
            val intent = Intent(this, PaqueteActivity::class.java)

            intent.putExtra(PAQUETE_ID, paquete.id)
            intent.putExtra(PAQUETE_NOMBRE, paquete.nombre)
            intent.putExtra(PAQUETE_DESCRIPCION, paquete.descripcion)
            intent.putExtra(PAQUETE_LINK_PAQUETE, paquete.link_paquete)
            intent.putExtra(PAQUETE_CODIGO, paquete.codigo)
            intent.putExtra(PAQUETE_PRECIO, paquete.precio.toLong())
            startActivity(intent)
        }
    }

    private fun observerPaquetesLiveData() {
        comercioViewModel.observePaquetesInventarioLiveData().observe(this, Observer { paquetes ->
            paquetesInventarioAdapter.setPaquetesList(paquetes)
        })
    }

    private fun prepararPaquetesRecyclerView() {
        paquetesInventarioAdapter = PaquetesInventarioAdapter()
        binding.rvPaquetes.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = paquetesInventarioAdapter
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