package cl.desafiolatam.pruebafinalcoins.repository

import android.content.Context
import androidx.lifecycle.LiveData
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem
import cl.desafiolatam.pruebafinalcoins.room.ProyectDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoRepository(var context:Context) {

    private val db=ProyectDataBase.getInstance(context)

    fun add(crypto:List<CryptoItem>){
        CoroutineScope(Dispatchers.IO).launch {
            db.cryptoDao().add(crypto)
        }
    }

    fun listar():LiveData<List<CryptoItem>> {
        return db.cryptoDao().list()
    }

    fun buscar(id:String) : LiveData<List<CryptoItem>> {
        return db.cryptoDao().searchDB(id)
    }
}