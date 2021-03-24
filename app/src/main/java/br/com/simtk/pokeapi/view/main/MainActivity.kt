package br.com.simtk.pokeapi.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.simtk.pokeapi.R
import br.com.simtk.pokeapi.adapter.OnItemClickListener
import br.com.simtk.pokeapi.adapter.PokemonAdapter
import br.com.simtk.pokeapi.model.Pokemons
import br.com.simtk.pokeapi.view.dialog.DialogDetails

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var TAG = "MainActivity"
    private lateinit var rv_list_pokemon: RecyclerView
    private lateinit var progress_circular: ProgressBar
    private lateinit var listViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents() //iniciando todos os componentes da view
        initObserver() //chamando observer
    }

    private fun initObserver() {
        listViewModel.pokemonList_.observe(this, Observer {
            lista(it) //passando lista recebida
        })
    }

    //iniciando componentes lateinit var
    private fun initComponents() {
        rv_list_pokemon = findViewById(R.id.rv_list_pokemon)
        progress_circular = findViewById(R.id.progress_circular)
        listViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        listViewModel.init()
    }

    fun lista(list: List<Pokemons>) {
        rv_list_pokemon!!.layoutManager = LinearLayoutManager(applicationContext)
        verificaProgress()
        rv_list_pokemon!!.adapter = PokemonAdapter(applicationContext, list, this)
    }

    //verifca progressBar ATiva
    fun verificaProgress() {
        if (progress_circular.isVisible) {
            progress_circular.visibility = View.GONE
        }
    }


    // implementar pesquisa
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_, menu)
        val menu_item = menu!!.findItem(R.id.search)

        if (menu_item != null) {
            val searchView = menu_item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(newText: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {

                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }


    //abrir detalhes do pokemon
    override fun onItemClicked(position: Int, pokemonClickList: Pokemons?) {
        val intent = Intent(this, DialogDetails::class.java)
        intent.putExtra("name", pokemonClickList?.name)
        intent.putExtra("imgURL", pokemonClickList?.imageURL)
        startActivity(intent)
    }
}
