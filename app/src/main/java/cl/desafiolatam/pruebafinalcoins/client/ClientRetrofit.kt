package cl.desafiolatam.pruebafinalcoins.client

import cl.desafiolatam.pruebafinalcoins.service.CryptoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientRetrofit {

    companion object{

        const val BASE_URL="https://api.coincap.io/v2/"
        private var client:Retrofit?=null

        fun getInstance(url:String) : CryptoService {
            if (client==null) {

                client = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

            }

            return  client!!.create(CryptoService::class.java)

        }
    }

}