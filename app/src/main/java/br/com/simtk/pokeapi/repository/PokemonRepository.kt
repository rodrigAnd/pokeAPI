package br.com.simtk.pokeapi.repository

import android.util.Log
import br.com.simtk.pokeapi.api.PokemonRestAPI
import br.com.simtk.pokeapi.model.Pokemons
import br.com.simtk.pokeapi.util.Util

class PokemonRepository(private val pokemonRestAPI: PokemonRestAPI) {

    private val TAG = "Pokemon Repository"
    private lateinit var pokemonList :List<Pokemons>

    fun listAllPokemon(limit: Int = 890):List<Pokemons> {
        val call = pokemonRestAPI.retrofitAPI().listAllPokemon(limit).execute()

        if (call.isSuccessful){

            call.body()?.results.let {
                Util.alterUrl(it) //função para alterar url e gerar imagem e id´s
                pokemonList = it!!
            }
        }else{
            call.errorBody().let {
                Log.d(TAG, it.toString())
            }
        }
        return pokemonList
    }
}