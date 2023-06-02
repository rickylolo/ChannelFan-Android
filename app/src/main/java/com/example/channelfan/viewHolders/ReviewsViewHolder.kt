package com.example.channelfan.viewHolders

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.models.ClassReseña

class ReviewsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nombreUsuario = view.findViewById<TextView>(R.id.textViewNombreUsuarioReview)
    val tituloReview = view.findViewById<TextView>(R.id.textViewTitulo)
    val descripcionReview = view.findViewById<TextView>(R.id.textViewDescripcion)
    val calificacionReview = view.findViewById<RatingBar>(R.id.rBCalificacionReview)
    val fechaReview = view.findViewById<TextView>(R.id.textViewFecha)





    fun render(reviewModel : ClassReseña){
        nombreUsuario.text = reviewModel.usuario?.firstName + reviewModel.usuario?.lastName
        tituloReview.text = reviewModel.titulo
        descripcionReview.text = reviewModel.descripcion
        calificacionReview.rating = reviewModel.calificacion?.toFloat() ?: 0.0f
        fechaReview.text = reviewModel.fecha
    }
}