package com.eduardo.pokeapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.models.Type
import com.squareup.picasso.Picasso
import android.graphics.Bitmap
import android.os.AsyncTask
import java.io.IOException


class TypeAdapter : BaseAdapter {

    private var typesList = ArrayList<Type>()
    private var context: Context? = null
    private var layoutInflater: LayoutInflater? = null

    constructor(
        context: Context,
        list: ArrayList<Type>,
        layoutInflater: LayoutInflater
    ) : super() {
        this.typesList = list
        this.context = context
        this.layoutInflater = layoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = layoutInflater?.inflate(R.layout.type_item, parent, false)
            vh = ViewHolder(view)
            view?.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = typesList[position].name!!.capitalize()
        val imageSource: String = Type.getImageType(typesList[position].name!!)!!
        var imageBitmap = getImageBitmapFromURL(imageSource);
        vh.tvImage.setImageBitmap(imageBitmap);

        return view
    }

    override fun getItem(position: Int): Any {
        return typesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return typesList.size
    }

    fun getImageBitmapFromURL(imageUrl: String?): Bitmap? {
        var imageBitmap: Bitmap? = null
        try {
            imageBitmap = object : AsyncTask<Void, Void, Bitmap>() {
                override fun doInBackground(vararg params: Void): Bitmap? {
                    try {
                        return Picasso.get().load(imageUrl).get()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return null
                }
            }.execute().get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return imageBitmap
    }


    inner class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvImage: ImageView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvImage = view?.findViewById(R.id.tvImage) as ImageView
        }
    }
}