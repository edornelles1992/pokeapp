package com.eduardo.pokeapp.models

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

}