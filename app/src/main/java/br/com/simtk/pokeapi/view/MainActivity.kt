package br.com.simtk.pokeapi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.simtk.pokeapi.R
import br.com.simtk.pokeapi.adapter.OnItemClickListener
import br.com.simtk.pokeapi.adapter.PokemonAdapter
import br.com.simtk.pokeapi.model.Pokemons
import br.com.simtk.pokeapi.retrofit.PokemonService


private var rv_list_pokemon: RecyclerView? = null
private var TAG = "MainActivity"


class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        showOpenList()
    }

    private fun initComponents() {
        rv_list_pokemon = findViewById(R.id.rv_list_pokemon)

    }

    private fun showOpenList() {
        Thread(Runnable {
            load()
        }).start()
    }

    fun load() {
        val pokemonsApi = PokemonService.listPokemon()
        pokemonsApi?.results?.run {
            for (i in 0..149) {
                val number = this.get(i).url.substring(34)
                val newNUmber = number.replace("/", "").toInt()
                this.get(i).imageURL = "https://pokeres.bastionbot.org/images/pokemon/$newNUmber.png"
            }
            lista(this)
        }
    }

    fun lista(list: List<Pokemons>) {

        rv_list_pokemon!!.layoutManager = LinearLayoutManager(applicationContext)
        rv_list_pokemon!!.adapter = PokemonAdapter(applicationContext, list, this)

    }


    override fun onItemClicked(position: Int, pokemonClickList: Pokemons?) {
        val intent = Intent(this, DialogDetails::class.java)
        intent.putExtra("name", pokemonClickList?.name)
        intent.putExtra("imgURL", pokemonClickList?.imageURL)
        startActivity(intent)
    }
}
