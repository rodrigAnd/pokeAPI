package br.com.simtk.pokeapi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonService {
    val BASE_URL = "https://pokeapi.co/api/v2/"
    var service: PokemonAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PokemonAPI::class.java)
    }
}

