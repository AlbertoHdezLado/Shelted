package com.example.android.shelted.Fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.core.text.set
import com.example.android.shelted.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_publish.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ShelterProfileFragment : Fragment() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference : CollectionReference = db.collection("users")

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_shelter_profile, container, false)

        val shelterName: TextView = v.findViewById(R.id.shelter_prof_name)
        val shelterEmail: TextView = v.findViewById(R.id.shelter_prof_email)

        val args = this.arguments
        val email = args?.get("email").toString()
        var name:String

        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        usersRef.document(email!!).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    name = document.getString("name").toString()
                    shelterName.text = name
                    shelterEmail.text = email
                    Log.d(TAG,"$email/$name/")
                } else {
                    Log.d(TAG, "The document doesn't exist.")
                }
            } else {
                task.exception?.message?.let {
                    Log.d(TAG, "Error in task")
                }
            }
        }

        return v
    }
}