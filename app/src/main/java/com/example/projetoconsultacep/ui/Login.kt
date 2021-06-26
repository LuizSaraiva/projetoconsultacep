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
import com.example.projetoconsultacep.HelperLogin
import com.example.projetoconsultacep.R
import com.example.projetoconsultacep.SingletonDataBase
import com.example.projetoconsultacep.model.User
import com.example.projetoconsultacep.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    lateinit var login: EditText
    lateinit var password: EditText

    lateinit var btnLogin: Button
    lateinit var btnCadastro: Button

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        initComponents()
        HelperLogin().getPreferencesLogin(this).let {
            login.setText(it.name)
            password.setText(it.password)
        }

        btnLogin.setOnClickListener {

            hideKeyboard(it)

            var userLogin = User(tv_usuario.text.toString(), tv_senha.text.toString())

            if (validUser(userLogin.name)) {

                //Intent

            } else {
                Snackbar.make(it, "Usuario não cadastrado", Snackbar.LENGTH_LONG).show()
            }
        }

        btnCadastro.setOnClickListener {
            startActivity(Intent(this, Cadastro::class.java))
        }

    }

    private fun initComponents() {

        viewModel = LoginViewModel()

        login = findViewById(R.id.tv_usuario)
        password = findViewById(R.id.tv_senha)

        btnLogin = findViewById(R.id.btn_login)
        btnCadastro = findViewById(R.id.btn_cadastrar)
    }

    private fun validUser(user: String): Boolean {

        val valid = viewModel.getLogin(user)

        if (valid.isEmpty()) {
            return false
        }
        return true
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}