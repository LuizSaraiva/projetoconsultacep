package com.example.projetoconsultacep.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetoconsultacep.R
import kotlinx.android.synthetic.main.activity_area_logada.*

class AreaLogada : AppCompatActivity() {

    companion object{
        const val NAME_EXTRA = "name"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_logada)

        initComponents()

        tv_sair.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        btn_consulta_cep.setOnClickListener {  }
    }

    private fun initComponents(){
        val nameUser = intent.getStringExtra(NAME_EXTRA)
        tv_name.setText(nameUser)
    }
}