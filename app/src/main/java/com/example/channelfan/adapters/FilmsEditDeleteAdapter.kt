package com.example.channelfan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R
import com.example.channelfan.viewHolders.FilmsEditDeleteViewHolder
import com.example.channelfan.viewHolders.FilmsViewHolder

class FilmsEditDeleteAdapter(private val FilmList:List<ClassPelicula>): RecyclerView.Adapter<FilmsEditDeleteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsEditDeleteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsEditDeleteViewHolder(layoutInflater.inflate(R.layout.item_edit_film, parent, false))
    }

    override fun onBindViewHolder(holder: FilmsEditDeleteViewHolder, position: Int) {
        val item = FilmList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = FilmList.size




}