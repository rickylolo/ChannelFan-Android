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
import com.example.channelfan.adapters.FilmsAdapter
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Favorites : AppCompatActivity() {
    private var listaPeliculas = arrayListOf<ClassPelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        //Hide Toolbar
        supportActionBar?.hide()

        obtenerPeliculas()

        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@Favorites,"Error 404, sesión expirada NO USER ID FOUND", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Favorites, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d("Login", idUsuario.toString())


        //Action Bar
        val btn_home = findViewById<ImageView>(R.id.btn_inicio)
        btn_home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Favorites, Home::class.java)
                startActivity(intent)
            }
        })

        val btn_profile = findViewById<ImageView>(R.id.btn_perfil)
        btn_profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Favorites, Profile::class.java)
                startActivity(intent)
            }
        })

        val btn_Search = findViewById<ImageView>(R.id.btn_search)
        btn_Search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Favorites, Search::class.java)
                startActivity(intent)
            }
        })
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFilms)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = FilmsAdapter(listaPeliculas)
    }

    private fun obtenerPeliculas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.MOVIE_WEB_SERVICE.obtenerPeliculas()
            runOnUiThread{
                if (call.isSuccessful){
                    listaPeliculas = call.body()!!.listaPeliculas
                    initRecyclerView()
                }else {
                    Toast.makeText(this@Favorites,"ERROR CONSULTAR,TODOS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}