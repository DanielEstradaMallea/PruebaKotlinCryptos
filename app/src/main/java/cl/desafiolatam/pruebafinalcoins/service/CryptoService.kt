package cl.desafiolatam.pruebafinalcoins.service

import cl.desafiolatam.pruebafinalcoins.model.Crypto
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem
import retrofit2.Call
import retrofit2.http.GET

interface CryptoService {

    @GET("assets")
    fun getCoins() : Call<Crypto>


}