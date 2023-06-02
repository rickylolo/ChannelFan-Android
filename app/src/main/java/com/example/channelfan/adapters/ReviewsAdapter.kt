package com.example.channelfan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R
import com.example.channelfan.models.ClassReseña
import com.example.channelfan.viewHolders.FilmsViewHolder
import com.example.channelfan.viewHolders.ReviewsViewHolder

class ReviewsAdapter(private val ReviewsList:List<ClassReseña>): RecyclerView.Adapter<ReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReviewsViewHolder(layoutInflater.inflate(R.layout.item_review, parent, false))
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val item = ReviewsList[position]
        holder.render(item)


    }

    override fun getItemCount(): Int = ReviewsList.size


}