package com.nonbinsys.greenersapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nonbinsys.greenersapp.databinding.ActivityMainBinding
import com.nonbinsys.greenersapp.ui.fragments.Toolbar
import com.nonbinsys.greenersapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //Configurations

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    fun onIniciarSesionClick(view: View) {
    val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}