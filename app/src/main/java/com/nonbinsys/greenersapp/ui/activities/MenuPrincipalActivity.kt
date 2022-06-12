package com.nonbinsys.greenersapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nonbinsys.greenersapp.comercio.ComercioActivity
import com.nonbinsys.greenersapp.comercio.ComerciosAdapter
import com.nonbinsys.greenersapp.databinding.ActivityMenuPrincipalBinding
import com.nonbinsys.greenersapp.viewmodel.MenuPrincipalViewModel

class MenuPrincipalActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuPrincipalBinding
    lateinit var viewModel: MenuPrincipalViewModel
    lateinit var comerciosAdapter: ComerciosAdapter


    companion object{
        const val COMERCIO_ID = "com.nonbinsys.greenersapp.ui.activities.comercioId"
        const val COMERCIO_NOMBRE = "com.nonbinsys.greenersapp.ui.activities.comercioNombre"
        const val COMERCIO_DIRECCION = "com.nonbinsys.greenersapp.ui.activities.comercioDireccion"
        const val COMERCIO_TELEFONO = "com.nonbinsys.greenersapp.ui.activities.comercioTelefono"
        const val COMERCIO_LINK_LOGO = "com.nonbinsys.greenersapp.ui.activities.comercioLinkLogo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Configurations
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MenuPrincipalViewModel::class.java]

        prepararComerciosRecyclerView()
        viewModel.getComercios()
        observerComerciosLiveData()

        onComercioClick()

        onSVNombreComercio()
    }

    private fun onSVNombreComercio() {
        binding.svNombreComercio.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.svNombreComercio.clearFocus()
                query?.let {
                    viewModel.encontrarComerciosporNombre(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun prepararComerciosRecyclerView() {
        comerciosAdapter = ComerciosAdapter()
        binding.rvComercios.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = comerciosAdapter
        }
    }

    private fun observerComerciosLiveData() {
        viewModel.observeComerciosLiveData().observe(this, Observer { comercios ->
            comerciosAdapter.setComerciosList(comercios)
        })
    }

    private fun onComercioClick() {
        comerciosAdapter.onItemClick = { comercio ->
            val intent = Intent(this, ComercioActivity::class.java)
            intent.putExtra(COMERCIO_ID, comercio.id.toString()
            )
            intent.putExtra(COMERCIO_NOMBRE, comercio.nombre)
            intent.putExtra(COMERCIO_DIRECCION, comercio.direccion)
            intent.putExtra(COMERCIO_TELEFONO, comercio.telf)
            intent.putExtra(COMERCIO_LINK_LOGO, comercio.link_logo)
            startActivity(intent)
        }
    }


}