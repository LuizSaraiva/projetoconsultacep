package com.example.projetoconsultacep.repository

import androidx.lifecycle.MutableLiveData
import com.example.projetoconsultacep.retrofit.ApiService
import com.example.projetoconsultacep.model.CEP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CEPApiDataSource {

    var ceps = MutableLiveData<CEP>()

    fun getCEP(): MutableLiveData<CEP> {

        val call = ApiService.service.getCEP("13178300")

        call.enqueue(object : Callback<CEP> {
            override fun onResponse(call: Call<CEP>, response: Response<CEP>) {

                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        val cep = CEP(
                            result.cep,
                            result.logradouro,
                            result.complemento,
                            result.bairro,
                            result.localidade,
                            result.uf,
                            result.ibge,
                            result.gia,
                            result.ddd
                        )
                        ceps.value = cep
                    }
                }
            }

            override fun onFailure(call: Call<CEP>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return ceps
    }
}