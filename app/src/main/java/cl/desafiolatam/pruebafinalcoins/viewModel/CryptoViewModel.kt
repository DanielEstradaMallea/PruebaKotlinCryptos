package cl.desafiolatam.pruebafinalcoins.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.desafiolatam.pruebafinalcoins.client.ClientRetrofit
import cl.desafiolatam.pruebafinalcoins.model.Crypto
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem
import cl.desafiolatam.pruebafinalcoins.repository.ClientRepository
import cl.desafiolatam.pruebafinalcoins.repository.CryptoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    private val clientRepo = ClientRepository()
    private val cryptoRepo = CryptoRepository(getApplication())

 //   val listCoins = MutableLiveData<Crypto>()



    val cryptos = cryptoRepo.listar()
    val crypto =MutableLiveData<CryptoItem>()
    val filter =MutableLiveData<String>()



    fun getData() {
       clientRepo.getCryptoList().enqueue(object : Callback<Crypto> {
            override fun onResponse(call: Call<Crypto>, response: Response<Crypto>) {
                response.body().let {

              //  listCoins.postValue(it)
                 cryptoRepo.add(it?.data!!)
                }

            }

            override fun onFailure(call: Call<Crypto>, t: Throwable) {
                Log.e("Call", t.message.toString())
            }

        })

    }

    fun updateCrypto(coin: CryptoItem) {

        this.crypto.value = coin

    }

}