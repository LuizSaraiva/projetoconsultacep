package com.example.projetoconsultacep

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    lateinit var login: EditText
    lateinit var password: EditText

    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()

        btnLogin.setOnClickListener {

            hideKeyboard(it)

            var userLogin = User(tv_usuario.text.toString(), tv_senha.text.toString())

            if (validUser(userLogin.name)) {

                //Intent

            } else {
                Snackbar.make(it, "Usuario não cadastrado", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun initComponents() {
        login = findViewById(R.id.tv_usuario)
        password = findViewById(R.id.tv_senha)

        btnLogin = findViewById(R.id.btn_login)
    }

    fun validUser(user: String): Boolean {

        val valid = SingletonDataBase.instance.helper?.searchUser(user) ?: return false

        if (valid.isEmpty()) {
            return false
        }
        return true
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}