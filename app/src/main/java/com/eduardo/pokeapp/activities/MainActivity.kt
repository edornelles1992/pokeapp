package com.eduardo.pokeapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.adapters.TypesAdapter
import com.eduardo.pokeapp.models.Type
import com.eduardo.pokeapp.services.PokeService
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private val typesList = arrayListOf<Type>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.findPokemonTypes()
        setContentView(R.layout.activity_main)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
        val p = ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        p.gravity = Gravity.CENTER
    }

    fun findPokemonTypes() {
        val callback = fun(data: JSONObject?) {
            if (data != null) {
                val types = data.getJSONArray("results")!! as JSONArray
                for (i in 0 until types.length()) {
                    val item = types.getJSONObject(i)
                    this.typesList.add(Type(item.getString("name"), item.getString("url")))
                }
                this.renderTypeList()
            }
        }
        PokeService.getTypes(callback)
    }

    fun findDetailsType(url : String) {
        val callback = fun(data: JSONObject?) {
            if (data != null) {
                Log.i("TESTE", data.toString())
            }
        }
        PokeService.getTypeDetail(callback,url)
    }

    fun renderTypeList(){
        var typesAdapter = TypesAdapter(this, typesList, this.layoutInflater)
        val intent = Intent(applicationContext, TypeDetailActivity::class.java)

        listViewType.adapter = typesAdapter
        listViewType.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            this.findDetailsType(typesList[position].url!!)
      //      intent.putExtra("myClass",typesList)
            startActivity(intent)
            Toast.makeText(this, "Click on " + typesList[position].name, Toast.LENGTH_SHORT).show()
        }
    }
}
