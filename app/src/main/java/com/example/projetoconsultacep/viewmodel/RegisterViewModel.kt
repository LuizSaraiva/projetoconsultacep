package com.example.projetoconsultacep.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projetoconsultacep.SingletonDataBase
import com.example.projetoconsultacep.model.User

class RegisterViewModel : ViewModel() {

    fun setRegister(user: User) {
        SingletonDataBase.instance.helper?.insertUser(user)
    }

    fun getLogin(user: String): List<User>{
        return SingletonDataBase.instance.helper?.searchUser(user)?: return mutableListOf()//repository().getUser(user)
    }

}