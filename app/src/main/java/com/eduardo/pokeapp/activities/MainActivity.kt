package com.eduardo.pokeapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.support.v7.app.ActionBar
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.adapters.TypeAdapter
import com.eduardo.pokeapp.models.Pokemon
import com.eduardo.pokeapp.models.Type
import com.eduardo.pokeapp.services.PokeService
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.findPokemonTypes()
        setContentView(R.layout.activity_main)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
        val p = ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        p.gravity = Gravity.CENTER
    }

    private fun findPokemonTypes() {
        val callback = fun(data: JSONObject?) {
            if (data != null) {
                  this.renderTypeList(Type.mountArrayTypes(data))
            }
        }
        PokeService.requestPokeApi(callback,"https://pokeapi.co/api/v2/type/")
    }

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

    private fun renderTypeList(typesList: ArrayList<Type>) {
        var typesAdapter = TypeAdapter(this, typesList, this.layoutInflater)
        listViewType.adapter = typesAdapter
        listViewType.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            this.findDetailsType(typesList[position].url!!)
            Toast.makeText(this, "Click on " + typesList[position].name, Toast.LENGTH_SHORT).show()
        }
    }
}
