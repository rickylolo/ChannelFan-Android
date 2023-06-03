package com.example.channelfan.adapters

import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.activities.Profile
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassReseña
import com.example.channelfan.viewHolders.MyReviewsViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyReviewsAdapter(private val ReviewsList:List<ClassReseña>): RecyclerView.Adapter<MyReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyReviewsViewHolder(layoutInflater.inflate(R.layout.item_edit_review, parent, false))
    }

    override fun onBindViewHolder(holder: MyReviewsViewHolder, position: Int) {
        val item = ReviewsList[position]
        holder.render(item, object : MyReviewsViewHolder.OnItemClickListener {
            override fun onDeleteClick(id: String) {
                val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setTitle("Eliminar reseña")
                alertDialogBuilder.setMessage("¿Estás seguro de que deseas eliminar esta reseña?")
                alertDialogBuilder.setPositiveButton("Eliminar") { dialog, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val call = RetrofitClient.REVIEW_WEB_SERVICE.borrarReseña(id)

                        val handler = Handler(holder.itemView.context.mainLooper)
                        handler.post {
                            if (call.isSuccessful) {

                                Toast.makeText(holder.itemView.context, "RESEÑA ELIMINADA", Toast.LENGTH_SHORT).show()
                                val intent = Intent(holder.itemView.context, Profile::class.java)
                                holder.itemView.context.startActivity(intent)

                            } else {
                                Toast.makeText(holder.itemView.context, "ERROR AL ELIMINAR RESEÑA", Toast.LENGTH_SHORT).show()
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