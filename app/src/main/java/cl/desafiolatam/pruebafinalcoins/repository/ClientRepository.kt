package cl.desafiolatam.pruebafinalcoins.repository

import cl.desafiolatam.pruebafinalcoins.client.ClientRetrofit
import cl.desafiolatam.pruebafinalcoins.model.Crypto
import retrofit2.Call

class ClientRepository {

    private val client= ClientRetrofit.getInstance(ClientRetrofit.BASE_URL)

    fun getCryptoList() : Call<Crypto> {
        return client.getCoins()
    }
}