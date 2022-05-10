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
import com.example.android.shelted.replaceFragment

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_login, container, false)

        // Get the activity and widget
        val context = activity as AppCompatActivity
        val btnNavigate: TextView = v.findViewById(R.id.logfragment_regbutt)

        // Replace fragment
        btnNavigate.setOnClickListener {
            context.replaceFragment(RegisterFragment())
        }

        return v
    }
}