package com.eduardo.pokeaap.service

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject


/**
 * Service class to make the HTTP requests
 * to Poke API.
 */
object PokeService {

    private val MAIN_URL = "https://pokeapi.co/api/v2/"
    private val TYPES = MAIN_URL + "type/"

    fun getTypes(callback: (JSONObject?) -> Unit) {
        var data : JSONObject? = null;

        Fuel.get(TYPES).responseJson() { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    data = result.getException() as JSONObject
                    callback.invoke(data)
                }
                is Result.Success -> {
                    data = result.get().obj()
                    callback.invoke(data)
                }
            }
        }

    }
}
