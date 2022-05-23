package com.example.android.shelted

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.Fragments.postListFragment
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.actionCodeSettings
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.android.shelted.Fragments.postFragment


class PostAdapter(options: FirestoreRecyclerOptions<Post>) :
    FirestoreRecyclerAdapter<Post, PostAdapter.PostAdapterVH>(options) {

    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterVH {

        return PostAdapterVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: PostAdapterVH, position: Int, model: Post) {
        holder.itemTitle.text = model.name
        holder.itemAge.text = model.age.toString()
        holder.itemDescription.text = model.description
        holder.itemLocation.text = "${model.city}, ${model.country}"
        Picasso
            .get()
            .load("https://firebasestorage.googleapis.com/v0/b/shelted-d5576.appspot.com/o/${model.path}?alt=media&token=f95e312c-97ac-468c-a281-5f0eea32b5a7")
            .resize(50, 50)
            .centerCrop()
            .into(holder.itemImage)
        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                val postFragment = postFragment()
                val fragmentManager: FragmentManager = activity.supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.logged_activity,postFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
    }

    class PostAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle = itemView.item_title
        var itemAge = itemView.item_age
        var itemDescription = itemView.item_description
        var itemLocation = itemView.item_location
        var itemImage = itemView.item_image
    }
}