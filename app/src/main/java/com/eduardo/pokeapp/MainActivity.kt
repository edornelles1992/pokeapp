package com.eduardo.pokeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.eduardo.pokeaap.service.PokeService
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
        val p = ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        p.gravity = Gravity.CENTER
    }

    fun testeAPI(view: View) {
        val callback = fun(data: JSONObject?){
            if (data != null) {
                Log.d("A","Tipos: " + data.getString("count")!!)
            }
        }
        PokeService.getTypes(callback)

    }
}
