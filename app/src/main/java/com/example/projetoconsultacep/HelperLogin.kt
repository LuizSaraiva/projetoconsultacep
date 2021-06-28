package com.example.projetoconsultacep

import android.content.Context
import android.hardware.usb.UsbRequest
import com.example.projetoconsultacep.model.User

class HelperLogin() {

    companion object {
        private val NAME_PREFERENCES = "login"
        private val KEY_REGISTER = "user_register"
        private val KEY_PASSWORD = "password_register"

    }

    fun setPreferencesLogin(context: Context, userRegister: User) {
        val sharedPreferences = context.getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE)
        sharedPreferences.edit().let { shared ->
            shared.putString(KEY_REGISTER, userRegister.name)
            shared.putString(KEY_PASSWORD, userRegister.password)
            shared.apply()
        }
    }

    fun getPreferencesLogin(context: Context): User {
        val sharedPreferences = context.getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE)

        val userRegister = User(
            (sharedPreferences.getString(KEY_REGISTER, "") ?: "") as String,
            (sharedPreferences.getString(KEY_PASSWORD, "") ?: "") as String
        )
        return userRegister
    }

}