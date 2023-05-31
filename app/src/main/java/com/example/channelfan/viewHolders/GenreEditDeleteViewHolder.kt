package com.example.channelfan.viewHolders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.models.ClassGenero

class GenreEditDeleteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val genreName = view.findViewById<TextView>(R.id.tvGenreNameRV)
    val btnEdit = view.findViewById<Button>(R.id.btnEditGenre)
    val btnDelete = view.findViewById<Button>(R.id.btnDeleteGenre)


    fun render(genreModel: ClassGenero, itemClickListener: OnItemClickListener) {
        genreName.text = genreModel.nombre

        btnEdit.setOnClickListener {
            itemClickListener.onEditClick(genreModel.id.toString())
        }

        btnDelete.setOnClickListener {
            itemClickListener.onDeleteClick(genreModel.id.toString())
        }
    }

    interface OnItemClickListener {
        fun onEditClick(id: String)
        fun onDeleteClick(id: String)
    }
}