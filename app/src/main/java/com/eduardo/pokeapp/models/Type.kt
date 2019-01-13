package com.eduardo.pokeapp.models

import org.json.JSONArray
import org.json.JSONObject

/**
 * Class representing de types received
 * by type pokemon requests.
 */
class Type {

    var name: String? = null
    var url: String? = null

    constructor(name: String?, url: String?) {
        this.name = name
        this.url = url
    }

    companion object {
        /**
         * Convert JSON array of types received by request to a ArrayList<Type>.
         */
        fun mountArrayTypes(data: JSONObject): ArrayList<Type> {
            val types = data.getJSONArray("results")!! as JSONArray
            val typesList = arrayListOf<Type>()
            for (i in 0 until types.length()) {
                val type = types.getJSONObject(i)
                typesList.add(Type(type.getString("name"), type.getString("url")))
            }
            return typesList
        }
    }
}