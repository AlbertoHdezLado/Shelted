package com.example.android.shelted.Fragments

import android.os.Bundle
import android.os.PatternMatcher
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.shelted.R
import com.example.android.shelted.RegisterFragment
import com.example.android.shelted.databinding.FragmentLoginBinding
import com.example.android.shelted.replaceFragment
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    val binding = FragmentLoginBinding.inflate(layoutInflater)
    private var email = ""
    private var pass = ""
    private var cont = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_login, container, false)
        // Get the activity and widget
        val context = activity as AppCompatActivity
        val btnNavigate: TextView = v.findViewById(R.id.logfragment_regbutt)
        val loginButton: Button = v.findViewById<Button>(R.id.logfragment_logbutt)
        // Replace fragment
        btnNavigate.setOnClickListener {
            context.replaceFragment(RegisterFragment())
        }
        //loggin button click
        firebaseAuth = FirebaseAuth.getInstance()
        loginButton.setOnClickListener {
            validateData()
            if(cont){
               // context.replaceFragment()
            }

        }

        return v
    }

    private fun validateData() {
        //get login data
            email = binding.editTextTextPersonName.toString().trim()
            pass = binding.editTextTextPassword.toString().trim()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.editTextTextPersonName.setError("Invalid e-mail format")
        }else if(TextUtils.isEmpty(pass)){ //no password given
            binding.editTextTextPassword.setError("Please insert a password")

        }else{//Data is given correctly
            firebaseLogin()


        }
    }

    private fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { //login succresful
                //get user data
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this.context, "Logged in successfully", Toast.LENGTH_SHORT).show()
                cont = true
            }
            .addOnFailureListener { e->
                Toast.makeText(this.context, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}