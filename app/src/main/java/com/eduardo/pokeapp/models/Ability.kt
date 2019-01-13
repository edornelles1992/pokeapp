package com.eduardo.pokeapp.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject

/**
 * Class Representing one pokemon's ability
 */
class Ability() : Parcelable {

    var name: String? = null
    var url: String? = null
    var secret: Boolean? = null
    var slot: Int? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        url = parcel.readString()
        secret = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        slot = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    constructor(name: String?, url: String?, secret: Boolean?, slot: Int?) : this(){
        this.name = name
        this.url = url
        this.secret = secret
        this.slot = slot
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeValue(secret)
        parcel.writeValue(slot)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ability> {
        override fun createFromParcel(parcel: Parcel): Ability {
            return Ability(parcel)
        }

        override fun newArray(size: Int): Array<Ability?> {
            return arrayOfNulls(size)
        }

        /**
         * Convert JSON array of abilities received by request to a ArrayList<Ability>.
         */
        fun mountArrayAbilities(data: JSONObject): ArrayList<Ability> {
            val abilities = data.getJSONArray("abilities")!! as JSONArray
            val abilityList = arrayListOf<Ability>()
            for (i in 0 until abilities.length()) {
                val ability = abilities.getJSONObject(i).getJSONObject("ability")
                val isHidden = abilities.getJSONObject(i).getBoolean("is_hidden")
                val slot = abilities.getJSONObject(i).getInt("slot")
                abilityList.add(Ability(ability.getString("name"), ability.getString("url"), isHidden, slot))
            }
            return abilityList
        }
    }
}