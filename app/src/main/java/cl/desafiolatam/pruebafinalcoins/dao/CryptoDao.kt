package cl.desafiolatam.pruebafinalcoins.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem


@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(crypto: List<CryptoItem>)

    @Query("select id,name,changePercent24Hr,explorer,marketCapUsd,maxSupply,priceUsd,rank,supply,symbol,volumeUsd24Hr,vwap24Hr from cryptos_table")
    fun list(): LiveData<List<CryptoItem>>

    @Query("select id,name,changePercent24Hr,explorer,marketCapUsd,maxSupply,priceUsd,rank,supply,symbol,volumeUsd24Hr,vwap24Hr" +
            " from cryptos_table where id like:id or symbol like :id")
    fun searchDB(id:String): LiveData<List<CryptoItem>>


}