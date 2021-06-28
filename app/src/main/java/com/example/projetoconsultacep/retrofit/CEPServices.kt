package com.example.projetoconsultacep.retrofit

import com.example.projetoconsultacep.model.CEP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CEPServices {

    @GET("{cep}/json/")
    fun getCEP(@Path("cep") cep: String): Call<CEP>

}