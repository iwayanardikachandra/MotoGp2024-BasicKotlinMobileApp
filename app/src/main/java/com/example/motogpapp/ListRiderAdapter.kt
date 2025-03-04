package com.example.motogpapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListRiderAdapter (private val listRider: ArrayList<Rider>) : RecyclerView.Adapter<ListRiderAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_riders, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photoUrl) = listRider[position]
        holder.tvName.text = name
        holder.tvDescription.text = description

        Glide.with(holder.itemView)
            .load(photoUrl)
            .placeholder(R.drawable.motogp) // Placeholder image while loading
            .error(R.drawable.motogp) // Image to display in case of error loading
            .into(holder.imgPhoto)


        // Set OnClickListener di sini
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_name", name)
            intentDetail.putExtra("key_description", description)
            intentDetail.putExtra("key_photo", photoUrl)
            holder.itemView.context.startActivity(intentDetail)
        }
        
    }

    override fun getItemCount(): Int = listRider.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

}