package com.example.projetoconsultacep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetoconsultacep.model.CEP
import com.example.projetoconsultacep.SingletonDataBase
import com.example.projetoconsultacep.model.User
import com.example.projetoconsultacep.repository.CEPApiDataSource

class LoginViewModel : ViewModel() {

    fun getLogin(user: String): List<User> {
        return SingletonDataBase.instance.helper?.searchUser(user)
            ?: return mutableListOf()//repository().getUser(user)
    }
}