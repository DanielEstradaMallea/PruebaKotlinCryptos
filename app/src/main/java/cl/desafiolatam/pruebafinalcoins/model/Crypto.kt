package cl.desafiolatam.pruebafinalcoins.model


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("data")
    val `data`: List<CryptoItem>,

    )