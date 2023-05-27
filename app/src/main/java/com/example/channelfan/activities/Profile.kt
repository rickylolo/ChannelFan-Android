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
import com.example.channelfan.databinding.ActivityMainBinding
import com.example.channelfan.databinding.ActivityProfileBinding
import com.example.channelfan.databinding.ActivityRegisterBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.models.ClassUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {
    private var listaPeliculas = arrayListOf<ClassPelicula>()
    private var userLoggeado = ClassUsuario()

    lateinit var binding: ActivityProfileBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Hide Toolbar
        supportActionBar?.hide()
        obtenerUser(idUsuario.toString())
        obtenerPeliculas()



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
                    Toast.makeText(this@Profile,"ERROR CONSULTAR,TODOS", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this@Profile,"ERROR CONSULTAR USUARIO", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}