package com.nonbinsys.greenersapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nonbinsys.greenersapp.databinding.ActivityMenuPrincipalBinding
import com.nonbinsys.greenersapp.viewmodel.MenuPrincipalViewModel

class MenuPrincipalActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuPrincipalBinding
    lateinit var viewModel: MenuPrincipalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //Configurations
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MenuPrincipalViewModel::class.java]

    }


}