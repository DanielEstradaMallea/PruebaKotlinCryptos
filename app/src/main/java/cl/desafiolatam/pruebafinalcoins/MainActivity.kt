package cl.desafiolatam.pruebafinalcoins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cl.desafiolatam.pruebafinalcoins.databinding.ActivityMainBinding
import cl.desafiolatam.pruebafinalcoins.viewModel.CryptoViewModel

class MainActivity : AppCompatActivity() {

    lateinit var b : ActivityMainBinding
    private val viewmodel by viewModels<CryptoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        viewmodel.getData()
    }
}