package com.example.channelfan.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R

class FilmsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val filmName = view.findViewById<TextView>(R.id.tvFilmName)
    val filmDescription = view.findViewById<TextView>(R.id.tvDescription)
    val filmPhoto = view.findViewById<ImageView>(R.id.ivFilm)


    fun render(filmsModel : ClassPelicula){
        filmName.text = filmsModel.titulo
        filmDescription.text = filmsModel.sinopsis
        Glide.with(filmPhoto.context).load(filmsModel.imagen).into(filmPhoto)
    }
}