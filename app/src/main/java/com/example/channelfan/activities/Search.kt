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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.adapters.BannerAdapter
import com.example.channelfan.adapters.HorizontalAdapter
import com.example.channelfan.providers.BannerProvider
import com.example.channelfan.providers.HorizontalProvider
import com.example.channelfan.R

class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //Hide Toolbar
        supportActionBar?.hide()
        initRecycler()
        initRecyclerView()
        initBannerView()


        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@Search,"Error 404, sesión expirada NO USER ID FOUND", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Search, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d("Login", idUsuario.toString())

        //Action Bar
        val btn_favorites = findViewById<ImageView>(R.id.btn_corazon)
        btn_favorites.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Search, Favorites::class.java)
                startActivity(intent)
            }
        })

        val btn_profile = findViewById<ImageView>(R.id.btn_perfil)
        btn_profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Search, Profile::class.java)
                startActivity(intent)
            }
        })

        val btn_home = findViewById<ImageView>(R.id.btn_inicio)
        btn_home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Search, Home::class.java)
                startActivity(intent)
            }
        })

    }
    private fun initRecycler(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHorizontal2)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = HorizontalAdapter(HorizontalProvider.horizontalList)

    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerReseñas)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        //recyclerView.adapter = FilmsAdapter(FilmsProvider.filmsList)
    }

    private fun initBannerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHorizontal)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = BannerAdapter(BannerProvider.BannerlList)
    }
}