package com.example.android.shelted.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.shelted.R
import com.example.android.shelted.RegisterFragment
import android.widget.Toast

import android.text.TextUtils

import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.example.android.shelted.Activities.LoggedActivity

import com.example.android.shelted.Activities.MainActivity

val auth: FirebaseAuth = FirebaseAuth.getInstance()

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_login, container, false)

        // Get the activity and widget
        val context = activity as AppCompatActivity
        val logButton: TextView = v.findViewById(R.id.logfragment_logbutt)
        val regButton: TextView = v.findViewById(R.id.logfragment_regbutt)
        val email: EditText = v.findViewById(R.id.emailField);
        val password: EditText = v.findViewById(R.id.passwordField);

        // Replace fragment
        logButton.setOnClickListener {
            val txt_email: String = email.text.toString()
            val txt_password: String = password.text.toString()

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                Toast.makeText(activity, "Empty Credentials!", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(txt_email, txt_password)
                //MainActivity().changeActivity()
                startActivity(Intent(activity, LoggedActivity::class.java))
            }
        }


        // Replace fragment
        regButton.setOnClickListener {
            val fragmentManager = context.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_activity,RegisterFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return v
    }
}

