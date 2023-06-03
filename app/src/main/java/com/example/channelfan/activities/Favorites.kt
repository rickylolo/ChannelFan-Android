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
import com.example.channelfan.adapters.MyReviewsAdapter
import com.example.channelfan.adapters.ReviewsAdapter
import com.example.channelfan.databinding.ActivityFavoritesBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassReseña
import com.example.channelfan.models.ClassUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Favorites : AppCompatActivity() {

    private var listaReviews = arrayListOf<ClassReseña>()

    private var userLoggeado = ClassUsuario()

    lateinit var binding: ActivityFavoritesBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Hide Toolbar
        supportActionBar?.hide()


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

        obtenerUser(idUsuario.toString())
        obtenerReseñasFavoritas(idUsuario.toString())



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

    fun initRecyclerViewReviews(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFilmsFavs)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = ReviewsAdapter(listaReviews)

    }


    fun obtenerUser(idUser : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.obtenerUsuario(idUser)
            runOnUiThread{
                if (call.isSuccessful){
                    userLoggeado = call.body()!!
                    binding.tvNameFav.text = "Hola," +userLoggeado.firstName + " "+ userLoggeado.lastName
                }else {
                    Toast.makeText(this@Favorites,"ERROR CONSULTAR USUARIO", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun obtenerReseñasFavoritas(idUser : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.REVIEW_WEB_SERVICE.obtenerReseñasFavoritas(idUser)
            runOnUiThread{
                if (call.isSuccessful){
                    listaReviews = call.body()!!.listaReseñas
                    initRecyclerViewReviews()
                }else {
                    Toast.makeText(this@Favorites,"No hay reseñas favoritas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}