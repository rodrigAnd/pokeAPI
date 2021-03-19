package br.com.simtk.pokeapi.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.simtk.pokeapi.R
import br.com.simtk.pokeapi.adapter.OnItemClickListener
import br.com.simtk.pokeapi.adapter.PokemonAdapter
import br.com.simtk.pokeapi.model.Pokemon
import br.com.simtk.pokeapi.model.Pokemons
import br.com.simtk.pokeapi.retrofit.PokemonAPI
import br.com.simtk.pokeapi.retrofit.PokemonService
import br.com.simtk.pokeapi.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var rv_list_pokemon: RecyclerView
    private lateinit var progress_circular: ProgressBar
    private lateinit var list: List<Pokemons>
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        listPokemon()
    }

    //iniciando componentes lateinit var
    private fun initComponents() {
        rv_list_pokemon = findViewById(R.id.rv_list_pokemon)
        progress_circular = findViewById(R.id.progress_circular)


        //componentes retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(PokemonService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        PokemonService.service = retrofit.create(PokemonAPI::class.java)


    }

    //recebendo dados da API
    fun listPokemon(limit: Int = 151) {
        val call = PokemonService.service.listPokemon(limit)

        call.enqueue(object : Callback<Pokemon?> {
            override fun onResponse(call: Call<Pokemon?>, response: Response<Pokemon?>) {
                if (response.isSuccessful) {
                    val util = Util(response.body()?.results)
                    response.body()?.results.let {
                        util.alterUrl()
                        list = it!!
                        lista()
                    }
                }
            }

            override fun onFailure(call: Call<Pokemon?>, t: Throwable) {
                Toast.makeText(applicationContext, "sem conexão: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    //inicianco lista Pokemon
    fun lista() {
        rv_list_pokemon!!.layoutManager = LinearLayoutManager(applicationContext)
        verificaProgress()
        rv_list_pokemon!!.adapter = PokemonAdapter(applicationContext, list, this)
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

    //verifca progressBar ATiva
    fun verificaProgress(){
        if (progress_circular.isVisible) {
            progress_circular.visibility = View.GONE
        }
    }


    //abrir detalhes do pokemon
    override fun onItemClicked(position: Int, pokemonClickList: Pokemons?) {
        val intent = Intent(this, DialogDetails::class.java)
        intent.putExtra("name", pokemonClickList?.name)
        intent.putExtra("imgURL", pokemonClickList?.imageURL)
        startActivity(intent)
    }
}
