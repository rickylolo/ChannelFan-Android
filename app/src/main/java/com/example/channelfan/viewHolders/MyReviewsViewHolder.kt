package com.example.channelfan.viewHolders

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.models.ClassReseña

class MyReviewsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nombreUsuario = view.findViewById<TextView>(R.id.Edit_textViewNombreUsuarioReview)
    val tituloReview = view.findViewById<TextView>(R.id.Edit_textViewTitulo)
    val descripcionReview = view.findViewById<TextView>(R.id.Edit_textViewDescripcion)
    val calificacionReview = view.findViewById<RatingBar>(R.id.Edit_rBCalificacionReview)
    val fechaReview = view.findViewById<TextView>(R.id.Edit_textViewFecha)
    val btnDelete = view.findViewById<Button>(R.id.btnDeleteReview)



    fun render(reviewModel : ClassReseña, itemClickListener: OnItemClickListener){
        nombreUsuario.text = reviewModel.usuario?.firstName + " "+ reviewModel.usuario?.lastName
        tituloReview.text = reviewModel.titulo
        descripcionReview.text = reviewModel.descripcion
        calificacionReview.rating = reviewModel.calificacion?.toFloat() ?: 0.0f
        fechaReview.text = reviewModel.fecha


        btnDelete.setOnClickListener {
            itemClickListener.onDeleteClick(reviewModel.id.toString())
        }
    }

    interface OnItemClickListener {
        fun onDeleteClick(idReview: String)
    }
}