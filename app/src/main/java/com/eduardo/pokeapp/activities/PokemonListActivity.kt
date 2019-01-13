package com.eduardo.pokeapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.adapters.PokemonAdapter
import com.eduardo.pokeapp.models.Ability
import com.eduardo.pokeapp.models.Movement
import com.eduardo.pokeapp.models.Pokemon
import com.eduardo.pokeapp.services.PokeService
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import org.json.JSONObject

class PokemonListActivity : AppCompatActivity() {

    private lateinit var pokemons: ArrayList<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemons = getIntent().getBundleExtra("data").getParcelableArrayList<Pokemon>("pokemons")
        setContentView(R.layout.activity_pokemon_list)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
        val p = ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        p.gravity = Gravity.CENTER
        this.renderTypeList(pokemons)
    }

    private fun findPokemonDetails(pokemon: Pokemon) {
        val callback = fun(data: JSONObject?) {
            if (data != null) {
                pokemon.setPokemonInfos(
                    Ability.mountArrayAbilities(data),
                    Movement.mountArrayMovements(data),
                    data.getInt("weight"),
                    data.getInt("height"),
                    data.getJSONObject("sprites").getString("front_default")
                )
                val intent = Intent(applicationContext, PokemonActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("pokemon", pokemon)
                intent.putExtra("data", bundle)
                startActivity(intent)
            }
        }
        PokeService.requestPokeApi(callback, pokemon.url!!)
    }

    private fun renderTypeList(pokemonList: ArrayList<Pokemon>) {
        var pokemonsAdapter = PokemonAdapter(this, pokemonList, this.layoutInflater)
        listViewPokemon.adapter = pokemonsAdapter
        listViewPokemon.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + pokemonList[position].name, Toast.LENGTH_SHORT).show()
            this.findPokemonDetails(pokemonList[position])
        }
    }
}
