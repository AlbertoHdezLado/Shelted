package com.example.android.shelted

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_layout.view.*
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import com.google.firebase.auth.ktx.actionCodeSettings


class PostAdapter(options: FirestoreRecyclerOptions<Post>) :
    FirestoreRecyclerAdapter<Post, PostAdapter.PostAdapterVH>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterVH {
        return PostAdapterVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: PostAdapterVH, position: Int, model: Post) {
        holder.itemTitle.text = model.name
        holder.itemAge.text = model.age.toString()
        holder.itemDescription.text = model.description
        holder.itemLocation.text = "${model.city}, ${model.country}"
        //holder.itemImage.setImageResource(model.mainImg)
    }

    class PostAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle = itemView.item_title
        var itemAge = itemView.item_age
        var itemDescription = itemView.item_description
        var itemLocation = itemView.item_location
        var itemImage = itemView.item_image


    }


}