package cl.desafiolatam.pruebafinalcoins.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.pruebafinalcoins.R
import cl.desafiolatam.pruebafinalcoins.databinding.ItemLayoutBinding
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem
import com.squareup.picasso.Picasso

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View, val listener: MiListener) :
        RecyclerView.ViewHolder(itemView) {


        private val binding = ItemLayoutBinding.bind(itemView)

        fun binData(cryptoItem: CryptoItem) {
            with(binding) {

                val symbol = cryptoItem.symbol.lowercase()



                val builder = StringBuilder()
                Picasso.get().load(linkGenerator(symbol)).resize(100, 100).into(imgCoin)
                txtNameCoin.text = cryptoItem.name
                txtUsdCoin.text =
                    builder.append("USD$ ", (cryptoItem.priceUsd).subSequence(0, 7)).append("")
                itemView.setOnClickListener {
                    listener.miOnClick(cryptoItem)
                }

            }
        }

       private fun linkGenerator(symbol: String): String {
            val link: String = symbol
            val linkComplete = "https://static.coincap.io/assets/icons/${
                link
            }@2x.png"
            return linkComplete
        }
    }



    private var list: List<CryptoItem> = ArrayList()
    lateinit var listener: MiListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate((R.layout.item_layout), parent, false)
        return CustomViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: List<CryptoItem>) {
        this.list = list
        notifyDataSetChanged()
    }


    interface MiListener {
        fun miOnClick(coin: CryptoItem)
    }

    fun setMiListener(listener: MiListener) {
        this.listener = listener
    }


}