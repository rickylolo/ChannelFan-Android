package com.example.channelfan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R
import com.example.channelfan.viewHolders.FilmsViewHolder

class FilmsAdapter(private val FilmList:List<ClassPelicula>): RecyclerView.Adapter<FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = FilmList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = FilmList.size


}