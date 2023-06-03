package com.example.channelfan.adapters

import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R
import com.example.channelfan.activities.Admin
import com.example.channelfan.activities.Genre
import com.example.channelfan.activities.Movie
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.viewHolders.FilmsEditDeleteViewHolder
import com.example.channelfan.viewHolders.FilmsViewHolder
import com.example.channelfan.viewHolders.GenreEditDeleteViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmsEditDeleteAdapter(private val FilmList:List<ClassPelicula>): RecyclerView.Adapter<FilmsEditDeleteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsEditDeleteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsEditDeleteViewHolder(layoutInflater.inflate(R.layout.item_edit_film, parent, false))
    }

    override fun onBindViewHolder(holder: FilmsEditDeleteViewHolder, position: Int) {
        val item = FilmList[position]

        holder.render(item, object : FilmsEditDeleteViewHolder.OnItemClickListener {
            override fun onEditClick(id: String) {
                val intent = Intent(holder.itemView.context, Movie::class.java)
                intent.putExtra("idPelicula", id)
                intent.putExtra("isEditando", true)
                holder.itemView.context.startActivity(intent)
            }

            override fun onDeleteClick(id: String) {
                val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setTitle("Eliminar Pelicula")
                alertDialogBuilder.setMessage("¿Estás seguro de que deseas eliminar esta pelicula?")
                alertDialogBuilder.setPositiveButton("Eliminar") { dialog, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val call = RetrofitClient.MOVIE_WEB_SERVICE.borrarPelicula(id)

                        val handler = Handler(holder.itemView.context.mainLooper)
                        handler.post {
                            if (call.isSuccessful) {
                                Toast.makeText(holder.itemView.context, "PELICULA ELIMINADO", Toast.LENGTH_SHORT).show()
                                val intent = Intent(holder.itemView.context, Admin::class.java)
                                holder.itemView.context.startActivity(intent)

                            } else {
                                Toast.makeText(holder.itemView.context, "ERROR AL ELIMINAR PELICULA", Toast.LENGTH_SHORT).show()
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

    override fun getItemCount(): Int = FilmList.size





}