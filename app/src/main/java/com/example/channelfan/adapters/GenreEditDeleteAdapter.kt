package com.example.channelfan.adapters

import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.activities.Admin
import com.example.channelfan.activities.Genre
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassGenero
import com.example.channelfan.viewHolders.GenreEditDeleteViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenreEditDeleteAdapter(private val GenreList:List<ClassGenero>): RecyclerView.Adapter<GenreEditDeleteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreEditDeleteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GenreEditDeleteViewHolder(layoutInflater.inflate(R.layout.item_genero, parent, false))
    }

    override fun onBindViewHolder(holder: GenreEditDeleteViewHolder, position: Int) {
        val genreModel = GenreList[position]

        holder.render(genreModel, object : GenreEditDeleteViewHolder.OnItemClickListener {
            override fun onEditClick(id: String) {
                val intent = Intent(holder.itemView.context, Genre::class.java)
                intent.putExtra("Id", id)
                intent.putExtra("isEditando", true)
                holder.itemView.context.startActivity(intent)
            }

            override fun onDeleteClick(id: String) {
                val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setTitle("Eliminar género")
                alertDialogBuilder.setMessage("¿Estás seguro de que deseas eliminar este género?")
                alertDialogBuilder.setPositiveButton("Eliminar") { dialog, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val call = RetrofitClient.GENRE_WEB_SERVICE.borrarGenero(id)

                        val handler = Handler(holder.itemView.context.mainLooper)
                        handler.post {
                            if (call.isSuccessful) {
                                Toast.makeText(holder.itemView.context, "GÉNERO ELIMINADO", Toast.LENGTH_SHORT).show()
                                val intent = Intent(holder.itemView.context, Admin::class.java)
                                holder.itemView.context.startActivity(intent)

                            } else {
                                Toast.makeText(holder.itemView.context, "ERROR AL ELIMINAR GÉNERO", Toast.LENGTH_SHORT).show()
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



    override fun getItemCount(): Int = GenreList.size




}