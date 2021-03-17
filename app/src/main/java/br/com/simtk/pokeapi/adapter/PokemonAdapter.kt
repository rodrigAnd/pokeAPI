package br.com.simtk.pokeapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.simtk.pokeapi.R
import br.com.simtk.pokeapi.model.Pokemons
import com.bumptech.glide.Glide


//criando lista de pokemons

class PokemonAdapter(
    var context: Context,
    private val pokemon: List<Pokemons>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private var TAG = "PokemonAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemon[position]
        holder.bindView(item)

    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: Pokemons) {
            val imgPokemon = (itemView).findViewById<ImageView>(R.id.img_pokemon)
            val tv_nomePokemon = (itemView).findViewById<TextView>(R.id.tv_nomePokemon)
            val tv_idPokemon = (itemView).findViewById<TextView>(R.id.tv_idPokemon)

            tv_nomePokemon.text = item.name
            Glide.with(context).load(item.imageURL).centerCrop().fitCenter().into(imgPokemon)
            tv_idPokemon.text = item.idPokemon.toString()


            //inicializando itens click
            initializer(item, listener)
        }

        private fun initializer(item: Pokemons, action: OnItemClickListener) {
            val imgPokemon = (itemView).findViewById<ImageView>(R.id.img_pokemon)
            val tv_nomePokemon = (itemView).findViewById<TextView>(R.id.tv_nomePokemon)
            val tv_idPokemon = (itemView).findViewById<TextView>(R.id.tv_idPokemon)

            tv_nomePokemon.text = item.name
            Glide.with(context).load(item.imageURL).centerCrop().fitCenter().into(imgPokemon)
            tv_idPokemon.text = item.idPokemon.toString()
            itemView.setOnClickListener{
                action.onItemClicked(adapterPosition, item)
            }
        }
    }
}






