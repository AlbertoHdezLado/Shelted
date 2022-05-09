package com.example.android.shelted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_register, container, false)

        // Get the activity and widget
        val context = activity as AppCompatActivity
        /*val btnNavigate: Button = v.findViewById(R.id.log_butt)

        // Replace fragment
        btnNavigate.setOnClickListener {
            context.replaceFragment(LoginFragment())
        }*/

        return v
    }
}