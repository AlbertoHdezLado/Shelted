package com.example.android.shelted.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.android.shelted.R
import com.squareup.picasso.Picasso

class postFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_post, container, false)

        val args = this.arguments

        val name = args?.get("name").toString()
        val age = args?.get("age").toString()
        val kind = args?.get("kind").toString()
        val country = args?.get("country").toString()
        val city = args?.get("city").toString()
        val cp = args?.get("cp").toString()
        val description = args?.get("description").toString()
        val imagePath = args?.get("imagePath").toString()

        val nameText: TextView = v.findViewById(R.id.post_pet_name)
        val ageText: TextView = v.findViewById(R.id.post_pet_age)
        val imageView: ImageView = v.findViewById(R.id.post_imageView)

        nameText.text = name
        ageText.text = age

        Picasso
            .get()
            .load("https://firebasestorage.googleapis.com/v0/b/shelted-d5576.appspot.com/o/${imagePath}?alt=media&token=f95e312c-97ac-468c-a281-5f0eea32b5a7")
            .resize(50, 50)
            .centerCrop()
            .into(imageView)

        return v
    }

}