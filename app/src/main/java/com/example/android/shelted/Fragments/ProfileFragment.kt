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

class ProfileFragment : Fragment() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference : CollectionReference = db.collection("users")

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        lateinit var pass: String
        lateinit var name: String
        lateinit var birth: String
        var shelter = false
        val profEmail: TextView = v.findViewById(R.id.prof_email)
        val profName: TextView = v.findViewById(R.id.prof_name)
        val profDate: TextView = v.findViewById(R.id.prof_date)
        val profPassword: TextView = v.findViewById(R.id.prof_password)
        val profShelter: Switch = v.findViewById(R.id.prof_shelter)

        val auth = FirebaseAuth.getInstance()
        val email = auth.currentUser?.email
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        usersRef.document(email!!).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    pass = document.getString("pass").toString()
                    name = document.getString("name").toString()
                    birth = document.getString("birth").toString()
                    shelter = document.getBoolean("shelter")!!
                    profEmail.text = email
                    profName.text = name
                    profDate.text = birth
                    profPassword.text = pass
                    profShelter.isChecked = shelter
                    profShelter.isClickable = false
                    profPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD;
                    Log.d(TAG,"$email/$pass/$birth/$shelter")
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