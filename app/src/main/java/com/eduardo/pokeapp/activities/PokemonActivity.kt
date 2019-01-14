package com.eduardo.pokeapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.*
import com.eduardo.pokeapp.R
import com.eduardo.pokeapp.adapters.AbilityAdapter
import com.eduardo.pokeapp.adapters.MovementAdapter
import com.eduardo.pokeapp.models.Ability
import com.eduardo.pokeapp.models.Movement
import com.eduardo.pokeapp.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon.*
import android.graphics.Bitmap
import android.net.Uri
import android.support.v4.app.FragmentActivity
import com.tarek360.instacapture.Instacapture
import com.tarek360.instacapture.listener.SimpleScreenCapturingListener
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import android.support.v4.content.FileProvider
import java.io.IOException

/**
 * Pokemon detail activity. Shows the basic pokemon
 * infos and the user can also share the pokemon status.
 */
class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemon: Pokemon = getIntent().getBundleExtra("data").getParcelable<Pokemon>("pokemon") as Pokemon
        setContentView(R.layout.activity_pokemon)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.header)
        this.renderContent(pokemon)
    }

    /**
     * Populate the activity components with the pokemon
     * selected.
     */
    private fun renderContent(pokemon: Pokemon) {
        textViewName.setText(pokemon.name?.capitalize())
        Picasso.get().load(pokemon.image).into(imageViewPokemon);
        textViewHeight.setText("Altura: ${pokemon.height}m")
        textViewWeight.setText("Peso: ${pokemon.weight}Kg")
        this.renderAbilityList(pokemon.abilities!!)
        this.renderMovementList(pokemon.moves!!)
    }

    /**
     * Invoked when share icon is Pressed.
     * Call the instaCapture to get the image to be
     * saved and shared by the saveImage function.
     */
    fun onClickShare(view: View) {
        textViewShare.setVisibility(View.INVISIBLE)
        Instacapture.capture(this, object : SimpleScreenCapturingListener() {
            override fun onCaptureComplete(bitmap: Bitmap) {
               saveImage(bitmap)
            }
        })
        textViewShare.setVisibility(View.VISIBLE)
    }

    /**
     * Saves the image as PNG to the app's cache directory
     * and call share method.
     */
    private fun saveImage(image: Bitmap) {
        val imagesFolder = File(cacheDir, "images")
        var uri: Uri? = null
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "shared_image.png")

            val stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
            uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file)
        } catch (e: IOException) {
            Log.d(FragmentActivity.INPUT_SERVICE, "IOException while trying to write file for sharing: ")
        }
        if (uri != null) {
            this.share(uri)
        }
    }

    /**
     * Receive the uri where image was saved and
     * share it.
     */
    private fun share(uri: Uri) {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/png"
        startActivity(intent)
    }

    /**
     * Create the pokemon abilities listView.
     */
    private fun renderAbilityList(abilityList: ArrayList<Ability>) {
        var abilitiesAdapter = AbilityAdapter(this, abilityList, this.layoutInflater)
        listViewAbilities.adapter = abilitiesAdapter
    }

    /**
     * Create the pokemon movements listView.
     */
    private fun renderMovementList(movementList: ArrayList<Movement>) {
        var movementsAdapter = MovementAdapter(this, movementList, this.layoutInflater)
        listViewMovements.adapter = movementsAdapter
    }
}
