package com.example.android.shelted.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.shelted.R

class messageListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_message_list, container, false)

        val sendButton = v.findViewById<Button>(R.id.button_gchat_send)

        fun generateId(length: Int= 20): String{ //ex: bwUIoWNCSQvPZh8xaFuz
            val alphaNumeric = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return alphaNumeric.shuffled().take(length).joinToString("")
        }

        return v
    }
}