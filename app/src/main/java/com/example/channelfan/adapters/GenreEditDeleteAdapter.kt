package com.example.channelfan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R
import com.example.channelfan.models.ClassGenero
import com.example.channelfan.viewHolders.FilmsEditDeleteViewHolder
import com.example.channelfan.viewHolders.FilmsViewHolder
import com.example.channelfan.viewHolders.GenreEditDeleteViewHolder

class GenreEditDeleteAdapter(private val GenreList:List<ClassGenero>): RecyclerView.Adapter<GenreEditDeleteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreEditDeleteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GenreEditDeleteViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun onBindViewHolder(holder: GenreEditDeleteViewHolder, position: Int) {
        val item = GenreList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = GenreList.size




}