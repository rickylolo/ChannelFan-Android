package com.example.channelfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.Adapters.BannerAdapter
import com.example.channelfan.Adapters.FilmsAdapter
import com.example.channelfan.Adapters.HorizontalAdapter

class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //Hide Toolbar
        supportActionBar?.hide()
        initRecycler()
        initRecyclerView()
        initBannerView()

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
        recyclerView.adapter = FilmsAdapter(FilmsProvider.filmsList)
    }

    private fun initBannerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHorizontal)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = BannerAdapter(BannerProvider.BannerlList)
    }
}