package cl.desafiolatam.pruebafinalcoins.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.desafiolatam.pruebafinalcoins.dao.CryptoDao
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem
import java.security.AccessControlContext

@Database(entities = [CryptoItem::class], version = 1)
abstract class ProyectDataBase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var instance: ProyectDataBase? = null

        fun getInstance(context: Context): ProyectDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        ProyectDataBase::class.java, "proyecto_db"
                    ).build()
                }
            }

            return instance!!
        }
    }
}