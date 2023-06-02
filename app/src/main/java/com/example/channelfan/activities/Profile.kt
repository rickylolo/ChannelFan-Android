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
import com.example.channelfan.adapters.ReviewsAdapter
import com.example.channelfan.databinding.ActivityProfileBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.models.ClassReseña
import com.example.channelfan.models.ClassUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {
    private var listaReviews = arrayListOf<ClassReseña>()

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

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@Profile,"Error 404, sesión expirada NO USER ID FOUND",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Profile, MainActivity::class.java)
            startActivity(intent)
        }

        //Hide Toolbar
        supportActionBar?.hide()
        obtenerUser(idUsuario.toString())
        obtenerReseñas(idUsuario.toString())





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


        // Floating Action Button

        val btn_Admin = findViewById<ImageView>(R.id.fabAdmin)
        btn_Admin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Profile, Admin::class.java)
                startActivity(intent)
            }
        })

        val btn_EditProfile = findViewById<ImageView>(R.id.fabEditProfile)
        btn_EditProfile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Profile, User::class.java)
                intent.putExtra("isEditando", true)
                startActivity(intent)
            }
        })


    }

    private fun initRecyclerViewReviews(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerReviews)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = ReviewsAdapter(listaReviews)

    }


    private fun obtenerReseñas(idUser : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.REVIEW_WEB_SERVICE.obtenerReseñasUsuario(idUser)
            runOnUiThread{
                if (call.isSuccessful){
                    listaReviews = call.body()!!.listaReseñas
                    initRecyclerViewReviews()
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