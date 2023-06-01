package com.example.channelfan.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.adapters.FilmsAdapter
import com.example.channelfan.adapters.FilmsEditDeleteAdapter
import com.example.channelfan.adapters.GenreEditDeleteAdapter
import com.example.channelfan.databinding.ActivityAdminBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassGenero
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.models.ClassUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Admin : AppCompatActivity() {
    private var listaPeliculas = arrayListOf<ClassPelicula>()
    private var listaGeneros = arrayListOf<ClassGenero>()
    private var userLoggeado = ClassUsuario()

    lateinit var binding: ActivityAdminBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Hide Toolbar
        supportActionBar?.hide()
        obtenerUser(idUsuario.toString())
        obtenerPeliculas()
        obtenerGeneros()



        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@Admin,"Error 404, sesión expirada NO USER ID FOUND",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Admin, MainActivity::class.java)
            startActivity(intent)
        }


        //Action Bar
        val btn_home = findViewById<ImageView>(R.id.btn_inicio)
        btn_home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Admin, Home::class.java)
                startActivity(intent)
            }
        })

        val btn_favorites = findViewById<ImageView>(R.id.btn_corazon)
        btn_favorites.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Admin, Favorites::class.java)
                startActivity(intent)
            }
        })

        val btn_Search = findViewById<ImageView>(R.id.btn_search)
        btn_Search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Admin, Search::class.java)
                startActivity(intent)
            }
        })


        // Floating Action Buttons
        val btn_Genero = findViewById<ImageView>(R.id.fabGenero)
        btn_Genero.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Admin, Genre::class.java)
                intent.putExtra("isEditando", false)
                startActivity(intent)
            }
        })
        val btn_Pelicula = findViewById<ImageView>(R.id.fabMovies)
        btn_Pelicula.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Admin, Movie::class.java)
                intent.putExtra("isEditando", false)
                startActivity(intent)
            }
        })

    }

    fun initRecyclerViewFilms(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFilmsEditDelete)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = FilmsEditDeleteAdapter(listaPeliculas)
    }

    fun initRecyclerViewGenres(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerGenresEditDelete)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = GenreEditDeleteAdapter(listaGeneros)
    }

    private fun obtenerPeliculas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.MOVIE_WEB_SERVICE.obtenerPeliculas()
            runOnUiThread{
                if (call.isSuccessful){
                    listaPeliculas = call.body()!!.listaPeliculas
                    initRecyclerViewFilms()
                }else {
                    Toast.makeText(this@Admin,"ERROR CONSULTAR,TODOS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun obtenerGeneros() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.GENRE_WEB_SERVICE.obtenerGeneros()
            runOnUiThread{
                if (call.isSuccessful){
                    listaGeneros = call.body()!!.listaGeneros
                    initRecyclerViewGenres()
                }else {
                    Toast.makeText(this@Admin,"ERROR CONSULTAR,TODOS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun obtenerUser(idUser : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.obtenerUsuario(idUser)
            runOnUiThread{
                if (call.isSuccessful){
                    userLoggeado = call.body()!!
                    binding.tvNombreProfile.text = userLoggeado.firstName
                    binding.tvUbicacionProfile.text = userLoggeado.address
                }else {
                    Toast.makeText(this@Admin,"ERROR CONSULTAR USUARIO", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}