package com.example.android.shelted.Activities

import android.app.Activity
import android.app.ProgressDialog
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
import com.example.android.shelted.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_publish.*
import java.util.*
import com.google.firebase.storage.StorageReference
import com.google.firebase.auth.FirebaseAuth


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
        newPost_spinner_kind.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,optionsAnimal)

        abrirGaleria()
        uploadPublish()
    }

    private fun uploadPublish() {
        newPost_publishButton.setOnClickListener {
            var path = UUID.randomUUID().toString()

            newPost.name = newPost_name.text.toString().trim()
            if (newPost_age.text.toString() != "")
                newPost.age = newPost_age.text.toString().toInt()
            newPost.kind = newPost_spinner_kind.selectedItem.toString().trim()
            newPost.country = newPost_country.text.toString().trim()
            newPost.city = newPost_city.text.toString().trim()
            newPost.cp = newPost_postalCode.text.toString().trim()
            newPost.description = newPost_description.text.toString().trim()

            if ((newPost.name == "") || (newPost.age!! < 0) || newPost.country == "" || newPost.cp == "" || newPost.description == "") {
                Toast.makeText(applicationContext, "Empty fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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

            val document = db.collection("posts").document()
            newPost.id = document.id
            db.collection("posts").document(newPost.id!!)

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