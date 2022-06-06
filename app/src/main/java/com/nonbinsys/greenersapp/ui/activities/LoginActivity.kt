package com.nonbinsys.greenersapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nonbinsys.greenersapp.databinding.ActivityLoginBinding
import com.nonbinsys.greenersapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //Configurations
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    fun onIniciarSesionClick(view: View) {
        //val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}