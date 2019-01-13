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

        fun getImageType(type: String): String? {
            val typeImage = when (type) {
                "normal" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png"
                "fighting" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/57.png"
                "flying" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png"
                "poison" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/110.png"
                "ground" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/28.png"
                "rock" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/95.png"
                "bug" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png"
                "ghost" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/94.png"
                "steel" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/82.png"
                "fire" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"
                "water" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png"
                "grass" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
                "electric" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png"
                "psychic" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/65.png"
                "ice" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/131.png"
                "dragon" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/149.png"
                "dark" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/510.png"
                "fairy" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/122.png"
                "unknown" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/87.png"
                "shadow" -> "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/88.png"
                else -> null
            }
            return typeImage
        }
    }
}