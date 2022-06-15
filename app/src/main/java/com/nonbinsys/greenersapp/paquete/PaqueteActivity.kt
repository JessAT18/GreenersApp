package com.nonbinsys.greenersapp.paquete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.R
import com.nonbinsys.greenersapp.comercio.ComercioActivity
import com.nonbinsys.greenersapp.database.PaqueteDatabase
import com.nonbinsys.greenersapp.databinding.ActivityPaqueteBinding
import com.nonbinsys.greenersapp.ui.activities.LoginActivity
import com.nonbinsys.greenersapp.ui.activities.MenuPrincipalActivity

class PaqueteActivity : AppCompatActivity() {
    //Datos paquete
    private var paqueteId: Long = 0
    private lateinit var paqueteNombre: String
    private lateinit var paqueteDescripcion: String
    private lateinit var paqueteLinkPaquete: String
    private lateinit var paqueteCodigo: String
    private var paquetePrecio: Long = 0
    private var cantidadPaquetes: Long = 1
    private var subTotal: Long = 0

    private var paqueteToSave: PaqueteCarrito? = null

    private lateinit var viewModel: PaqueteViewModel
    private lateinit var binding: ActivityPaqueteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityPaqueteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this)[PaqueteViewModel::class.java]
        val paqueteDatabase = PaqueteDatabase.getInstance(this)
        val viewModelFactory = PaqueteViewModelFactory(paqueteDatabase)
        viewModel = ViewModelProvider(this, viewModelFactory)[PaqueteViewModel::class.java]

        getPaqueteInformationFromIntent()
        setInformationPaquete()

        observerPaquetesLiveData()

        onAgregarPedidoClick()

        actualizarSubtotal()
    }

    private fun observerPaquetesLiveData() {
        viewModel.observePaqueteLiveData().observe(this, object: Observer<Paquete> {
            override fun onChanged(t: Paquete?) {
                //onResponseCase()
                val miPaquete = t
                val nuevoPaquete = PaqueteCarrito(miPaquete!!.id, paqueteCodigo, paquetePrecio.toFloat(), miPaquete.nombre, cantidadPaquetes)
                paqueteToSave = nuevoPaquete
            }

        })
    }

    private fun onAgregarPedidoClick() {
        binding.btnAgregarPaquete.setOnClickListener {

            val nuevoPaquete = PaqueteCarrito(paqueteId, paqueteCodigo, paquetePrecio.toFloat(), paqueteNombre, cantidadPaquetes)
            paqueteToSave = nuevoPaquete

            paqueteToSave?.let { paqueteGuardar ->
                viewModel.insertPaquete(paqueteGuardar)
                Toast.makeText(this, "Paquete añadido al carrito de compras", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getPaqueteInformationFromIntent() {
        paqueteId = intent.getLongExtra(ComercioActivity.PAQUETE_ID, 0)
        paqueteNombre = intent.getStringExtra(ComercioActivity.PAQUETE_NOMBRE)!!
        paqueteDescripcion = intent.getStringExtra(ComercioActivity.PAQUETE_DESCRIPCION)!!
        paqueteLinkPaquete = intent.getStringExtra(ComercioActivity.PAQUETE_LINK_PAQUETE)!!
        paqueteCodigo = intent.getStringExtra(ComercioActivity.PAQUETE_CODIGO)!!
        paquetePrecio = intent.getLongExtra(ComercioActivity.PAQUETE_PRECIO,0)
    }

    private fun setInformationPaquete() {
        Glide.with(applicationContext)
            .load(paqueteLinkPaquete)
            .into(binding.imagenPaquete)

        binding.ctbPaquete.title = paqueteNombre
        binding.tvNombrePaquete.text = paqueteNombre
        binding.tvDescripcion.text = paqueteDescripcion
        binding.tvPrecio.text = "Bs $paquetePrecio"
    }

    fun onMasClick(view: View) {
        cantidadPaquetes++
        binding.tvCantidadPaquetes.text = cantidadPaquetes.toString()

        actualizarSubtotal()
    }

    private fun actualizarSubtotal() {
        subTotal = cantidadPaquetes*paquetePrecio
        binding.tvSubtotal.text = "Bs $subTotal"
    }

    fun onMenosClick(view: View) {
        cantidadPaquetes--
        if (cantidadPaquetes <= 0) {
            cantidadPaquetes = 1
        }
        binding.tvCantidadPaquetes.text = cantidadPaquetes.toString()

        actualizarSubtotal()
    }
}