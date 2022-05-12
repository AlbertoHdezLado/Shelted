package com.example.android.shelted

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf("Max", "Snake", "Tom", "Arnold", "Pet1", "pet2", "pet3", "pet4", "pet5")
    private var details = arrayOf("Max details", "Snake details", "Tom details", "Arnold details",
        "Pet1 details", "pet2 details", "pet3 details", "pet4 details", "pet5 details")
    private var images = intArrayOf(R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280,
        R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280, R.drawable.pet_3635985_1280)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

            itemView.setOnClickListener{
                //val position: Int = adapterPosition
                //val position: Int = bindingAdapterPosition

                Toast.makeText(itemView.context, "you clicked on ${titles[position]}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}