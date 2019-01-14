package com.eduardo.pokeapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.AdapterView
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.adapters.TypeAdapter
import com.eduardo.pokeapp.models.Pokemon
import com.eduardo.pokeapp.models.Type
import com.eduardo.pokeapp.services.PokeService
import kotlinx.android.synthetic.main.activity_main.*

import org.json.JSONObject

/**
 * Main activity that start the application showing
 * the type lists available of pokemons.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.findPokemonTypes()
        setContentView(R.layout.activity_main)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
    }

    /**
     * Call the PokeService to requests the type list
     * passing a callback to manage the received data.
     */
    private fun findPokemonTypes() {
        val callback = fun(data: JSONObject?) {
            if (data != null) {
                  this.renderTypeList(Type.mountArrayTypes(data))
            }
        }
        PokeService.requestPokeApi(callback,"https://pokeapi.co/api/v2/type/")
    }

    /**
     * Call PokeService to requests the pokemons by type
     * with the received url parameter passing a callback
     * to manage the received data.
     */
    private fun findDetailsType(url : String) {
        val callback = fun(data: JSONObject?) {
            if (data != null) {
                val intent = Intent(applicationContext, PokemonListActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("pokemons",Pokemon.mountArrayPokemon(data))
                intent.putExtra("data",bundle )
                startActivity(intent)
            }
        }
        PokeService.requestPokeApi(callback,url)
    }

    /**
     * Create the type listView with an received
     * ArrayList<Type>.
     */
    private fun renderTypeList(typesList: ArrayList<Type>) {
        var typesAdapter = TypeAdapter(this, typesList, this.layoutInflater)
        listViewType.adapter = typesAdapter
        listViewType.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            this.findDetailsType(typesList[position].url!!)
        }
    }
}
