package com.nonbinsys.greenersapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.nonbinsys.greenersapp.R

class Toolbar {
    fun show(activities: AppCompatActivity, tittle: String, upButton: Boolean) {
        activities.setSupportActionBar(activities.findViewById(R.id.Tb_toolbar))
        activities.supportActionBar?.title = tittle
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}