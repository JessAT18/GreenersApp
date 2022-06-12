package com.nonbinsys.greenersapp.paquete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nonbinsys.greenersapp.R

class PaqueteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paquete)
    }
}