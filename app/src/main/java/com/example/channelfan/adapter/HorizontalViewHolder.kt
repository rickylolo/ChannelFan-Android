package com.example.channelfan.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.Horizontal
import com.example.channelfan.R

    class HorizontalViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val HorizonName = view.findViewById<TextView>(R.id.tvTitulo)
        val HorizonPhoto = view.findViewById<ImageView>(R.id.ivImagen)


        fun render(HorizonModel : Horizontal){
            HorizonName.text = HorizonModel.nombre
            Glide.with(HorizonPhoto.context).load(HorizonModel.photo).into(HorizonPhoto)
        }
    }
