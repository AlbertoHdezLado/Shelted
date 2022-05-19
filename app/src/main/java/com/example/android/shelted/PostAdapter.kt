package com.example.android.shelted

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_layout.view.*


class PostAdapter(options: FirestoreRecyclerOptions<Post>) :
    FirestoreRecyclerAdapter<Post, PostAdapter.PostAdapterVH>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterVH {
        return PostAdapterVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: PostAdapterVH, position: Int, model: Post) {
        holder.itemTitle.text = model.name
        holder.itemDetail.text = model.description
    }

    class PostAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle = itemView.item_title
        var itemDetail = itemView.item_detail
    }


}