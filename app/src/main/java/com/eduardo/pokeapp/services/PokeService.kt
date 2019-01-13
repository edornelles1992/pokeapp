package com.eduardo.pokeapp.services

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject

/**
 * Service object to make the HTTP requests
 * to Poke API.
 */
object PokeService {

    /**
     * Request to PokeAPI with an received URL string and a callback
     * to do something with the data.
     */
    fun requestPokeApi(callback: (JSONObject?) -> Unit, url: String) {
        var data: JSONObject? = null;

        Fuel.get(url).responseJson() { request, response, result ->
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
