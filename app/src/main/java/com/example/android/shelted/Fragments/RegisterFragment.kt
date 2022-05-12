package com.example.android.shelted

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android.shelted.Activities.LoggedActivity
import com.example.android.shelted.Activities.MainActivity
import com.example.android.shelted.Fragments.LoginFragment
import com.example.android.shelted.Fragments.auth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    lateinit var username: EditText
    lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confpassword: EditText
    lateinit var name: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_register, container, false)


        // View Bindings
        val context = activity as AppCompatActivity
        username = v.findViewById(R.id.reg_username)
        email = v.findViewById(R.id.reg_email)
        password = v.findViewById(R.id.reg_password)
        confpassword = v.findViewById(R.id.reg_confpassword)
        name = v.findViewById(R.id.reg_name)
        val regbutton: Button = v.findViewById(R.id.regfragment_regbutt)
        val logbutton: TextView = v.findViewById(R.id.regfragment_logbutt)

        // Initialising auth object
        auth = Firebase.auth

        regbutton.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        logbutton.setOnClickListener {
            val fragmentManager = context.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_activity, LoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return v
    }

    private fun signUpUser() {
        val usernameText = username.text.toString()
        val emailText = email.text.toString()
        val passwordText = password.text.toString()
        val confpasswordText = confpassword.text.toString()
        val nameText = name.text.toString()

        // check pass
        if (usernameText.isBlank() || emailText.isBlank() || passwordText.isBlank() || confpasswordText.isBlank() || nameText.isBlank()) {
            Toast.makeText(activity, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (passwordText != confpasswordText) {
            Toast.makeText(activity, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(emailText, passwordText)
        val context = activity as AppCompatActivity
        val fragmentManager = context.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity,LoginFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}