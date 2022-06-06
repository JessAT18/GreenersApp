package com.nonbinsys.greenersapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nonbinsys.greenersapp.adapters.ComerciosAdapter
import com.nonbinsys.greenersapp.databinding.ActivityMenuPrincipalBinding
import com.nonbinsys.greenersapp.ui.fragments.Toolbar
import com.nonbinsys.greenersapp.viewmodel.MenuPrincipalViewModel

class MenuPrincipalActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuPrincipalBinding
    lateinit var viewModel: MenuPrincipalViewModel
    lateinit var comerciosAdapter: ComerciosAdapter


    companion object{
        const val COMERCIO_ID = "com.nonbinsys.greenersapp.ui.activities.comercioId"
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
            intent.putExtra(COMERCIO_ID, comercio.id)
            startActivity(intent)
        }
    }


}