package cl.desafiolatam.pruebafinalcoins.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatam.pruebafinalcoins.databinding.FragmentDetailsBinding
import cl.desafiolatam.pruebafinalcoins.viewModel.CryptoViewModel
import com.squareup.picasso.Picasso
import java.lang.StringBuilder


class DetailsFragment : Fragment() {

    lateinit var b: FragmentDetailsBinding
    private val viewmodel by activityViewModels<CryptoViewModel>()
    lateinit var boton: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentDetailsBinding.inflate(layoutInflater)


        viewmodel.crypto.observe(viewLifecycleOwner, Observer {

            with(b) {
                Picasso.get().load(
                    "https://static.coincap.io/assets/icons/${
                        it.symbol.lowercase()
                    }@2x.png"
                ).resize(250, 250).into(imgCoinDetail)


                val link = it.explorer
                txtSimbol.text = it.symbol
                txtNameinfo.text = it.name

                val builder = StringBuilder()
                val builder1 = StringBuilder()
                val builder2 = StringBuilder()


                txtPriceUSD.text =
                    builder.append("USD$ ", it.priceUsd).append(" ").subSequence(0, 12)

                txtSupply.text =
                    builder1.append("Supply: ", it.supply).append(" ").subSequence(0, 25)

                txtMarketCap.text =
                    builder2.append("Market Cap: ", it.marketCapUsd).append(" ").subSequence(0, 25)

                btnExplorer.setOnClickListener {
                    boton = Uri.parse(link)
                    val intento = Intent(Intent.ACTION_VIEW, boton)
                    startActivity(intento)

                }
            }
        })
        return b.root
    }


}