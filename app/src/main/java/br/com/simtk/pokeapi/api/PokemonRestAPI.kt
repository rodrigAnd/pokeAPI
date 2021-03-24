package br.com.simtk.pokeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRestAPI {
    val BASE_URL = "https://pokeapi.co/api/v2/"
    var service: PokemonAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PokemonAPI::class.java)
    }

    //configurando o retrofit
    private fun pokemonProvider(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitAPI(): PokemonAPI = pokemonProvider().create(PokemonAPI::class.java)




}

