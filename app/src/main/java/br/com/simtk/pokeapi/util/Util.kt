package br.com.simtk.pokeapi.util

import android.util.Log
import br.com.simtk.pokeapi.model.Pokemons

class Util(private val pokemon: List<Pokemons>?) {
    fun alterUrl() {
        Log.d("URL POKEMON", "alterUrl: " + pokemon)
        for (i: Int in 0..149) {

            val number = pokemon?.get(i)?.url?.substring(34)
            val newNUmber = number?.replace("/", "")?.toInt()
            pokemon?.get(i)?.imageURL =
                "https://pokeres.bastionbot.org/images/pokemon/$newNUmber.png"
            pokemon?.get(i)?.idPokemon = newNUmber
        }
    }
}