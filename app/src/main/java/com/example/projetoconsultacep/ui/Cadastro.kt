package com.example.projetoconsultacep.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.projetoconsultacep.R
import com.example.projetoconsultacep.model.User
import com.example.projetoconsultacep.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_cadastro.*

class Cadastro : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var toolbar: Toolbar

    lateinit var btnRegister: Button

    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        initComponents()

        btnRegister.setOnClickListener { it ->

            val userRegister = User(user.text.toString(), password.text.toString())

            if (userRegister.name.isEmpty() || userRegister.password.isEmpty()) {
                Toast.makeText(this, R.string.preencha_todos_campos, Toast.LENGTH_LONG).show()
                hideKeyboard(it)
            } else {

                if (viewModel.getLogin(userRegister.name).isNotEmpty()) {
                    Toast.makeText(this, R.string.usuario_ja_cadastrado, Toast.LENGTH_LONG).show()
                    hideKeyboard(it)
                } else {
                    viewModel.setRegister(userRegister)
                    Toast.makeText(this, R.string.cadastro_realizado, Toast.LENGTH_LONG).show()
                    hideKeyboard(it)

                    val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
                    sharedPreferences.edit().let { shared ->
                        shared.putString("user_register", userRegister.name)
                        shared.putString("password_register", userRegister.password)
                        shared.apply()
                    }
                    redirect()
                }

            }
        }
    }

    private fun initComponents() {

        user = ed_user
        password = ed_password
        toolbar = toolbarCad
        btnRegister = btn_register

        viewModel = RegisterViewModel()

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun redirect() {
        startActivity(Intent(this, Login::class.java))
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}