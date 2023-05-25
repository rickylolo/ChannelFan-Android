package com.example.channelfan.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.Films
import com.example.channelfan.R

class FilmsAdapter(private val FilmList:List<Films>): RecyclerView.Adapter<FilmsViewHolder>() {

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