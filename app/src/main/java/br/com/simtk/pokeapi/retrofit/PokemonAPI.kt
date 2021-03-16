package br.com.simtk.pokeapi.retrofit

import br.com.simtk.pokeapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon")
    fun listPokemon(@Query("limit")limit: Int): Call<Pokemon>

}