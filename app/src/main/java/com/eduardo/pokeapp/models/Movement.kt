package com.eduardo.pokeapp.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject

class Movement : Parcelable {

    var name: String? = null
    var url: String? = null

    constructor(parcel: Parcel) {
        name = parcel.readString()
        url = parcel.readString()
    }

    constructor(name: String, url: String?) {
        this.name = name
        this.url = url
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movement> {
        override fun createFromParcel(parcel: Parcel): Movement {
            return Movement(parcel)
        }

        override fun newArray(size: Int): Array<Movement?> {
            return arrayOfNulls(size)
        }

        /**
         * Convert JSON array of abilities received by request to a ArrayList<Ability>.
         */
        fun mountArrayMovements(data: JSONObject): ArrayList<Movement> {
            val moves = data.getJSONArray("moves")!! as JSONArray
            val movementList = arrayListOf<Movement>()
            for (i in 0 until moves.length()) {
                val move = moves.getJSONObject(i).getJSONObject("move")
                movementList.add(Movement(move.getString("name"), move.getString("url")))
            }
            return movementList
        }
    }
}