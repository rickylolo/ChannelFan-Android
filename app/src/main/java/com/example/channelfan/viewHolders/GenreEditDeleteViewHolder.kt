package com.example.channelfan.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.models.ClassGenero

class GenreEditDeleteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val genreName = view.findViewById<TextView>(R.id.tvGenreNameRV)



    fun render(genreModel : ClassGenero){
        genreName.text = genreModel.nombre

    }
}