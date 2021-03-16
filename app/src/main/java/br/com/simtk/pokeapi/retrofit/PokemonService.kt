package br.com.simtk.pokeapi.retrofit

import br.com.simtk.pokeapi.model.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonService {
     val BASE_URL = "https://pokeapi.co/api/v2/"
     private var service: PokemonAPI

     init {
         val  retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
          service = retrofit.create(PokemonAPI::class.java)
     }

     fun listPokemon(limit: Int = 150) : Pokemon?{
         val call =  service.listPokemon(limit)
         return call.execute().body()
     }
}