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

//fun listPokemon(limit: Int = 150) {
//
////         val call =  service.listPokemon(limit)
////         return call.execute().body()
//    val call = PokemonService.service.listPokemon(limit)
//    call.enqueue(object : Callback<Pokemon?> {
//        override fun onResponse(call: Call<Pokemon?>, response: Response<Pokemon?>) {
//            if (response.isSuccessful) {
//                response.body()
//            }
//
//        }
//
//        override fun onFailure(call: Call<Pokemon?>, t: Throwable) {
//
//        }
//    })
//}
//
