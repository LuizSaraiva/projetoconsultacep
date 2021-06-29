package com.example.projetoconsultacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_consulta_cep.*

class ConsultaCEP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_cep)

        initComponents()

    }

    private fun initComponents(){

        setSupportActionBar(toolbarCad)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        toolbarCad.setNavigationOnClickListener {
            onBackPressed()
        }

    }

}
