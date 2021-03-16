package br.com.simtk.pokeapi.adapter

import br.com.simtk.pokeapi.model.Pokemons

interface OnItemClickListener {
    fun onItemClicked(position: Int, pokemonClickList: Pokemons?)
}
