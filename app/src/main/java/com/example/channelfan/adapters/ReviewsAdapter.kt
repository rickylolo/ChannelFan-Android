package com.example.channelfan.adapters

import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassReseña
import com.example.channelfan.viewHolders.ReviewsViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewsAdapter(private val ReviewsList:List<ClassReseña>): RecyclerView.Adapter<ReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReviewsViewHolder(layoutInflater.inflate(R.layout.item_review, parent, false))
    }


    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val item = ReviewsList[position]
        holder.render(item, object : ReviewsViewHolder.OnItemClickListener {



            override fun onLikeClick(idUsuario: String, idReview: String) {
                val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setTitle("Like reseña")
                alertDialogBuilder.setMessage("¿Estás seguro de que agregar esta reseña a favoritos?")
                alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
                    CoroutineScope(Dispatchers.IO).launch {

                        val body = HashMap<String, String>()
                        body["idUsuario"] = idUsuario
                        body["idReview"] = idReview

                        val call = RetrofitClient.USER_WEB_SERVICE.agregarReseñaFavoritos(body)

                        val handler = Handler(holder.itemView.context.mainLooper)
                        handler.post {
                            if (call.isSuccessful) {
                                Toast.makeText(holder.itemView.context, "Agregado a Favoritos Correctamente", Toast.LENGTH_SHORT).show()


                            } else {
                                Toast.makeText(holder.itemView.context, "Ya agregaste esta reseña a favoritos", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    dialog.dismiss()
                }
                alertDialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }

        })


    }

    override fun getItemCount(): Int = ReviewsList.size


}