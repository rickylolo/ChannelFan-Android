package com.example.channelfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.Adapters.FilmsAdapter

class Favorites : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        //Hide Toolbar
        supportActionBar?.hide()

        initRecyclerView()

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
        recyclerView.adapter = FilmsAdapter(FilmsProvider.filmsList)
    }
}