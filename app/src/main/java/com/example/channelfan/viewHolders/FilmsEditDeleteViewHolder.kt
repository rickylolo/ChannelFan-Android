package com.example.channelfan.viewHolders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R

class FilmsEditDeleteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val filmName = view.findViewById<TextView>(R.id.tv_edit_filmName)
    val filmDescription = view.findViewById<TextView>(R.id.tv_edit_description)
    val filmPhoto = view.findViewById<ImageView>(R.id.iv_edit_film)
    val btnEditarFilm = view.findViewById<Button>(R.id.btn_edit_film)
    val btnDeleteFilm = view.findViewById<Button>(R.id.btn_delete_film)


    fun render(filmsModel: ClassPelicula, itemClickListener: OnItemClickListener){
        filmName.text = filmsModel.titulo
        filmDescription.text = filmsModel.sinopsis
        Glide.with(filmPhoto.context).load(filmsModel.imagen).into(filmPhoto)


        btnEditarFilm.setOnClickListener {
            itemClickListener.onEditClick(filmsModel.id.toString())
        }

        btnDeleteFilm.setOnClickListener {
            itemClickListener.onDeleteClick(filmsModel.id.toString())
        }
    }

    interface OnItemClickListener {
        fun onEditClick(id: String)
        fun onDeleteClick(id: String)
    }
}