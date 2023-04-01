package com.example.channelfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.adapter.FilmsAdapter

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Hide Toolbar
        supportActionBar?.hide()
        initRecyclerView()
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFilms)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = FilmsAdapter(FilmsProvider.filmsList)
    }
}