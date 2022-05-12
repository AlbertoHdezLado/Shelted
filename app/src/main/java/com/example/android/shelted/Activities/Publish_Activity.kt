package com.example.android.shelted.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_logged.*
import kotlinx.android.synthetic.main.activity_publish.*

class Publish_Activity : AppCompatActivity() {

    var PICK_IMAGE_MULTIPLE = 1
    val optionsAnimal = arrayOf("Dog", "Cat", "Rabbit", "Bird")
    var imagePicker: ImageView? = null
    val db =  Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        imagePicker = findViewById(R.id.fotoAnimal)
        spinner_TipoAnimal.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,optionsAnimal)

        abrirGaleria()
        botonInicio()
    }

    private fun botonInicio() {
        inicioButton.setOnClickListener {volverInicio()}
    }

    private fun volverInicio() {
        val homeIntent: Intent = Intent(this, logged_activity::class.java)

        startActivity(homeIntent!!)
    }


    private fun abrirGaleria() {

        seleccionarImg.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE)
        }

    }
}