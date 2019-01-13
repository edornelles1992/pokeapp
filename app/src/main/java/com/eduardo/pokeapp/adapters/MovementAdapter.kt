package com.eduardo.pokeapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.models.Ability
import com.eduardo.pokeapp.models.Movement

class MovementAdapter : BaseAdapter {

    private var movementList = ArrayList<Movement>()
    private var context: Context? = null
    private var layoutInflater: LayoutInflater? = null

    constructor(
        context: Context,
        list: ArrayList<Movement>,
        layoutInflater: LayoutInflater
    ) : super() {
        this.movementList = list
        this.context = context
        this.layoutInflater = layoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = layoutInflater?.inflate(R.layout.movement_item, parent, false)
            vh = ViewHolder(view)
            view?.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = movementList[position].name!!.capitalize()

        return view
    }

    override fun getItem(position: Int): Any {
        return movementList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movementList.size
    }

    inner class ViewHolder(view: View?) {
        val tvTitle: TextView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
        }
    }
}