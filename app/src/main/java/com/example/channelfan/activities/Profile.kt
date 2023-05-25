package com.example.channelfan.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //Hide Toolbar
        supportActionBar?.hide()

        initRecyclerView()
        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@Profile,"Error 404, sesión expirada NO USER ID FOUND",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Profile, MainActivity::class.java)
            startActivity(intent)
        }


        //Action Bar
        val btn_home = findViewById<ImageView>(R.id.btn_inicio)
        btn_home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Profile, Home::class.java)
                startActivity(intent)
            }
        })

        val btn_favorites = findViewById<ImageView>(R.id.btn_corazon)
        btn_favorites.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Profile, Favorites::class.java)
                startActivity(intent)
            }
        })

        val btn_Search = findViewById<ImageView>(R.id.btn_search)
        btn_Search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Profile, Search::class.java)
                startActivity(intent)
            }
        })

        //BOTONES FLOTANTES
        //CREAR PELICULA
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this@Profile, RegisterMovie::class.java)
            startActivity(intent)
        }
        //CREAR Reseña
        val fabReview: View = findViewById(R.id.fabReview)
        fabReview.setOnClickListener { view ->
            val intent = Intent(this@Profile, RegisterReview::class.java)
            startActivity(intent)
        }
        //CREAR GENEROS
        val fabGEN: View = findViewById(R.id.fabGeneros)
        fabGEN.setOnClickListener { view ->
            val intent = Intent(this@Profile, RegisterGenre::class.java)
            startActivity(intent)
        }

    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFilms)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        //recyclerView.adapter = FilmsAdapter(FilmsProvider.filmsList)
    }
}