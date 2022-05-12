package com.example.android.shelted.Activities

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_logged.*
import kotlinx.android.synthetic.main.activity_publish.*
import java.util.*
import com.google.firebase.storage.StorageReference

class Publish_Activity : AppCompatActivity() {


    var PICK_IMAGE_MULTIPLE = 1
    var imageEncoded: Uri? = null
    var imagePicker: ImageView? = null
    val db =  Firebase.firestore
    var newPost: Post = Post("",0,"","","","","","","")

    val optionsAnimal = arrayOf("Dog", "Cat", "Rabbit", "Bird")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        imagePicker = findViewById(R.id.fotoAnimal)
        spinner_TipoAnimal.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,optionsAnimal)

        abrirGaleria()
        botonInicio()
        uploadPublish()
    }

    private fun uploadPublish() {
        publishButton.setOnClickListener {

            if (imageEncoded != null) {
                var pd = ProgressDialog(this)
                pd.setTitle("Uploading image")
                pd.show()
                var path = UUID.randomUUID().toString()
                var imgRef: StorageReference =  FirebaseStorage.getInstance().reference.child("PRESENTACION")

                newPost.path = path
                imgRef.putFile(imageEncoded!!)
                    .addOnSuccessListener { p0 ->
                        pd.dismiss()
                        Toast.makeText(
                            applicationContext,
                            "Imagen subida con éxito",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .addOnFailureListener { p0 ->
                        pd.dismiss()
                        Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { p0 ->
                        var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                        pd.setMessage("Uploaded ${progress.toInt()} %")
                    }
            }

            newPost.name = post_name.text.toString().trim()
            newPost.age = post_age.text.toString().toInt()
            newPost.kind = spinner_TipoAnimal.selectedItem.toString().trim()
            newPost.country = post_country.text.toString().trim()
            newPost.city = post_city.text.toString().trim()
            newPost.cp = post_postalCode.text.toString().trim()
            newPost.desciption = post_description.text.toString().trim()

            db.collection("Publicaciones").document(post_name.text.toString().trim()).set(newPost)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }

            volverInicio()
        }
    }

    private fun botonInicio() {
        inicioButton.setOnClickListener {volverInicio()}
    }

    private fun volverInicio() {
        val homeIntent: Intent = Intent(this, logged_activity::class.java)

        startActivity(homeIntent)
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