package com.eduardo.pokeapp.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject

/**
 * Class representing one Pokemon.
 */
class Pokemon() : Parcelable {

    var name: String? = null
    var url: String? = null
    var abilities: ArrayList<Ability>? = null
    var moves: ArrayList<Movement>? = null
    var weight: Int = 0
    var height: Int = 0
    var image: String? = null

    constructor(name: String?, url: String?) : this() {
        this.name = name
        this.url = url
    }

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        url = parcel.readString()
        weight = parcel.readInt()
        height = parcel.readInt()
        abilities = parcel.readArrayList(Ability::class.java.classLoader) as ArrayList<Ability>?
        moves = parcel.readArrayList(Movement::class.java.classLoader) as ArrayList<Movement>?
        image = parcel.readString()
    }

    fun setPokemonInfos(abilities: ArrayList<Ability>?, moves: ArrayList<Movement>?, weight: Int, height: Int, image: String?) {
        this.abilities = abilities
        this.moves = moves
        this.weight = weight
        this.height = height
        this.image = image
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeInt(weight)
        parcel.writeInt(height)
        parcel.writeList(abilities)
        parcel.writeList(moves)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }

        /**
         * Convert JSON array of pokemons received by request to a ArrayList<Pokemon>.
         */
        fun mountArrayPokemon(data: JSONObject): ArrayList<Pokemon> {
            val pokemons = data.getJSONArray("pokemon")!! as JSONArray
            val pokemonList = arrayListOf<Pokemon>()
            for (i in 0 until pokemons.length()) {
                val pokemon = pokemons.getJSONObject(i).getJSONObject("pokemon")
                pokemonList.add(Pokemon(pokemon.getString("name"), pokemon.getString("url")))
            }
            return pokemonList
        }
    }
}