package com.example.android.shelted.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.android.shelted.R
import com.squareup.picasso.Picasso

class postFragment : Fragment() {
    @SuppressLint("SetTextI18n")
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
        val shelter = args?.get("shelter").toString()

        val nameText: TextView = v.findViewById(R.id.post_pet_name)
        val ageText: TextView = v.findViewById(R.id.post_pet_age)
        val imageView: ImageView = v.findViewById(R.id.post_imageView)
        val descriptionText: TextView = v.findViewById(R.id.postDescription)
        val locationText: TextView = v.findViewById(R.id.post_pet_location)
        val kindText: TextView = v.findViewById(R.id.post_pet_kind)

        nameText.text = name
        ageText.text = age
        descriptionText.text = description
        locationText.text = "$city, $country"
        kindText.text = kind

        var path = imagePath
        if (path == "")
            path = kind.lowercase() + ".png"

        Picasso
            .get()
            .load("https://firebasestorage.googleapis.com/v0/b/shelted-d5576.appspot.com/o/${path}?alt=media&token=f95e312c-97ac-468c-a281-5f0eea32b5a7")
            .resize(1000, 1000)
            .centerCrop()
            .into(imageView)

        val buttonShelter = v.findViewById<Button>(R.id.post_button_shelter)

        buttonShelter.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("email", shelter)

            val activity=v!!.context as AppCompatActivity
            val shelterProfileFragment = ShelterProfileFragment()
            shelterProfileFragment.arguments = bundle
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.logged_activity,shelterProfileFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return v
    }

}