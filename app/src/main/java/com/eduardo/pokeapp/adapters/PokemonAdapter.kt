package com.eduardo.pokeapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.models.Pokemon
import com.eduardo.pokeapp.models.Type

class PokemonAdapter : BaseAdapter {

    private var pokemonList = ArrayList<Pokemon>()
    private var context: Context? = null
    private var layoutInflater: LayoutInflater? = null

    constructor(
        context: Context,
        list: ArrayList<Pokemon>,
        layoutInflater: LayoutInflater
    ) : super() {
        this.pokemonList = list
        this.context = context
        this.layoutInflater = layoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = layoutInflater?.inflate(R.layout.pokemon_item, parent, false)
            vh = ViewHolder(view)
            view?.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = pokemonList[position].name
        vh.tvContent.text = pokemonList[position].url

        return view
    }

    override fun getItem(position: Int): Any {
        return pokemonList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return pokemonList.size
    }

    inner class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById(R.id.tvContent) as TextView
        }
    }
}