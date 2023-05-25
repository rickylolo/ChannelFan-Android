package com.example.channelfan.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.R
import com.example.channelfan.models.ClassPelicula

class HorizontalViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val HorizonName = view.findViewById<TextView>(R.id.tvTitulo)
        val HorizonPhoto = view.findViewById<ImageView>(R.id.ivImagen)


        fun render(HorizonModel : ClassPelicula){
            HorizonName.text = HorizonModel.titulo
            Glide.with(HorizonPhoto.context).load(HorizonModel.imagen).into(HorizonPhoto)
        }
    }
