package com.example.android.shelted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_register, container, false)

        val registerButton: Button = v.findViewById<Button>(R.id.register_butt)
        firebaseAuth = FirebaseAuth.getInstance()
        registerButton.setOnClickListener {


        }

        return v
    }
}