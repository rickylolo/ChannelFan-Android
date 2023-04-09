package com.example.channelfan.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.Films
import com.example.channelfan.R

class FilmsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val filmName = view.findViewById<TextView>(R.id.tvFilmName)
    val filmDescription = view.findViewById<TextView>(R.id.tvDescription)
    val filmPhoto = view.findViewById<ImageView>(R.id.ivFilm)


    fun render(filmsModel : Films){
        filmName.text = filmsModel.nombreFilm
        filmDescription.text = filmsModel.descripcion
        Glide.with(filmPhoto.context).load(filmsModel.photo).into(filmPhoto)
    }
}