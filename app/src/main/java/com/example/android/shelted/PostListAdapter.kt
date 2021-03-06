package com.example.android.shelted

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout_post.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.android.shelted.Fragments.postFragment

import android.content.res.ColorStateList


class PostListAdapter(options: FirestoreRecyclerOptions<Post>) :
    FirestoreRecyclerAdapter<Post, PostListAdapter.PostAdapterVH>(options) {

    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterVH {

        return PostAdapterVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_post, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostAdapterVH, position: Int, model: Post) {
        holder.itemTitle.text = model.name
        holder.itemAge.text = model.age.toString()
        holder.itemDescription.text = model.description
        holder.itemLocation.text = "${model.city}, ${model.country}"
        when(model.kind) {
            "Dog" -> holder.itemView.setBackgroundColor((Color.parseColor("#98fb98")))
            "Cat" -> holder.itemView.setBackgroundColor((Color.parseColor("#ffcb94")))
            "Rabbit" -> holder.itemView.setBackgroundColor((Color.parseColor("#ff94ff")))
            "Bird" -> holder.itemView.setBackgroundColor((Color.parseColor("#94feff")))
        }

        var path = model.path
        if (path == "")
            path = model.kind.toString().lowercase() + ".png"

        Picasso
            .get()
            .load("https://firebasestorage.googleapis.com/v0/b/shelted-d5576.appspot.com/o/${path}?alt=media&token=f95e312c-97ac-468c-a281-5f0eea32b5a7")
            .resize(1000, 1000)
            .centerCrop()
            .into(holder.itemImage)
        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val bundle = Bundle()
                bundle.putString("name", model.name)
                bundle.putString("age", model.age.toString())
                bundle.putString("kind", model.kind)
                bundle.putString("country", model.country)
                bundle.putString("city", model.city)
                bundle.putString("cp", model.cp)
                bundle.putString("description", model.description)
                bundle.putString("imagePath", model.path)
                bundle.putString("shelter", model.shelter)

                val activity=v!!.context as AppCompatActivity
                val postFragment = postFragment()
                postFragment.arguments = bundle
                val fragmentManager: FragmentManager = activity.supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.logged_activity,postFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked))

        val colors = intArrayOf(
            Color.parseColor("#FFFF00"),
            Color.parseColor("#FFFFFF"))

        holder.checkBoxFavourite.buttonTintList = ColorStateList(states, colors)

        holder.checkBoxFavourite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            }
            else {

            }
        }


    }

    class PostAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle = itemView.item_title
        var itemAge = itemView.item_age
        var itemDescription = itemView.item_description
        var itemLocation = itemView.item_location
        var itemImage = itemView.item_image
        var checkBoxFavourite = itemView.checkBox_Favourite
    }
}