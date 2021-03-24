package br.com.simtk.pokeapi.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.simtk.pokeapi.api.PokemonRestAPI
import br.com.simtk.pokeapi.model.Pokemons
import br.com.simtk.pokeapi.repository.PokemonRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val pokemonRestAPI = PokemonRestAPI
    private val pokemonRepository = PokemonRepository(pokemonRestAPI)
    private var pokemonList = MutableLiveData<List<Pokemons>>()
    val pokemonList_ = pokemonList

    fun init(){
        getAllPokemon()
    }

    private fun getAllPokemon() {
        GlobalScope.launch {
            obterDados()
        }
    }

    suspend fun obterDados(){
        pokemonList.postValue(pokemonRepository.listAllPokemon())
    }
}


