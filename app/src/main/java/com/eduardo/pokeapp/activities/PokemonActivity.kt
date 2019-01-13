package com.eduardo.pokeapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.Gravity
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.adapters.AbilityAdapter
import com.eduardo.pokeapp.adapters.MovementAdapter
import com.eduardo.pokeapp.adapters.PokemonAdapter
import com.eduardo.pokeapp.models.Ability
import com.eduardo.pokeapp.models.Movement
import com.eduardo.pokeapp.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.activity_pokemon_list.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemon: Pokemon = getIntent().getBundleExtra("data").getParcelable<Pokemon>("pokemon") as Pokemon
        setContentView(R.layout.activity_pokemon)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
        val p = ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        p.gravity = Gravity.CENTER
        this.renderContent(pokemon)
    }

    fun renderContent(pokemon: Pokemon) {
        textViewName.setText(pokemon.name?.capitalize())
        Picasso.get().load(pokemon.image).into(imageViewPokemon);
        textViewHeight.setText("Altura: ${pokemon.height}m")
        textViewWeight.setText("Peso: ${pokemon.weight}Kg")
        this.renderAbilityList(pokemon.abilities!!)
        this.renderMovementList(pokemon.moves!!)
    }

    private fun renderAbilityList(abilityList: ArrayList<Ability>) {
        var abilitiesAdapter = AbilityAdapter(this, abilityList, this.layoutInflater)
        listViewAbilities.adapter = abilitiesAdapter
    }

    private fun renderMovementList(movementList: ArrayList<Movement>) {
        var movementsAdapter = MovementAdapter(this, movementList, this.layoutInflater)
        listViewMovements.adapter = movementsAdapter
    }
}
