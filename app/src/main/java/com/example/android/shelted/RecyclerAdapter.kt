package com.example.android.shelted

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post


class RecyclerAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    lateinit var context: Context
    lateinit var postArrayList: ArrayList<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        val post:Post = postArrayList[position]
        holder.itemTitle.text = post.name
        holder.itemDetail.text = post.desciption
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemDetail: TextView = itemView.findViewById(R.id.item_detail)
    }

}