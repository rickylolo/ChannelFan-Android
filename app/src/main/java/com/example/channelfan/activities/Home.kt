package com.example.channelfan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.adapters.FilmsAdapter
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {
    private var listaPeliculas = arrayListOf<ClassPelicula>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Hide Toolbar
        supportActionBar?.hide()
        obtenerPeliculas()

        val userId = intent.getStringExtra("userId")
        Log.d("Login", userId.toString())


        //Action Bar
        val btn_favorites = findViewById<ImageView>(R.id.btn_corazon)
        btn_favorites.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Home, Favorites::class.java)
                startActivity(intent)
            }
        })

        val btn_profile = findViewById<ImageView>(R.id.btn_perfil)
        btn_profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Home, Profile::class.java)
                startActivity(intent)
            }
        })

        val btn_Search = findViewById<ImageView>(R.id.btn_search)
        btn_Search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Home, Search::class.java)
                startActivity(intent)
            }
        })
    }

    private fun initRecyclerView(){
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
                    Toast.makeText(this@Home,"ERROR CONSULTAR,TODOS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}