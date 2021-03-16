package br.com.simtk.pokeapi.model

data class Pokemon(
    val count: Int? = null,
    val next: String? = null,
    val previous: Any? = null,
    val results: List<Pokemons>,


)