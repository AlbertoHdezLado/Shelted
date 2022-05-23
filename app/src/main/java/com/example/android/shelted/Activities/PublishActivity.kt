package com.example.android.shelted.Activities

import android.app.Activity
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
import androidx.appcompat.app.ActionBar
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.Fragments.auth
import com.example.android.shelted.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_logged.*
import kotlinx.android.synthetic.main.activity_publish.*
import java.util.*
import com.google.firebase.storage.StorageReference
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser




class PublishActivity : AppCompatActivity() {


    var PICK_IMAGE_MULTIPLE = 1
    var imageEncoded: Uri? = null
    var imagePicker: ImageView? = null
    val db =  Firebase.firestore
    var newPost: Post = Post("","",0,"","","","","","","", "")

    val optionsAnimal = arrayOf("Dog", "Cat", "Rabbit", "Bird")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        setContentView(R.layout.activity_publish)
        imagePicker = findViewById(R.id.fotoAnimal)
        spinner_TipoAnimal.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,optionsAnimal)

        abrirGaleria()
        botonInicio()
        uploadPublish()
    }

    private fun uploadPublish() {
        publishButton.setOnClickListener {
            var path = UUID.randomUUID().toString()

            var imgRef: StorageReference =  FirebaseStorage.getInstance().reference.child(path)
            if (imageEncoded != null) {
                var pd = ProgressDialog(this)
                pd.setTitle("Uploading image")
                pd.show()

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

            val name = post_name.text.toString().trim()
            val document = db.collection("posts").document(name)
            newPost.id = document.id
            newPost.name = post_name.text.toString().trim()
            newPost.age = post_age.text.toString().toInt()
            newPost.kind = spinner_TipoAnimal.selectedItem.toString().trim()
            newPost.country = post_country.text.toString().trim()
            newPost.city = post_city.text.toString().trim()
            newPost.cp = post_postalCode.text.toString().trim()
            newPost.description = post_description.text.toString().trim()

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                println(user.toString())
            } else {
                println("NO")
            }

            val handle = document.set(newPost)

            handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
            handle.addOnFailureListener { Log.e("Firebase", "Error writing document $it") }
            startActivity(Intent(this, LoggedActivity::class.java))
        }

    }

    private fun botonInicio() {
        inicioButton.setOnClickListener {finish()}
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageEncoded = data!!.data
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_MULTIPLE){
            if (imageEncoded != null) {                // if single image is selected
                fotoAnimal.setImageURI(imageEncoded)
            }
        }
    }
}