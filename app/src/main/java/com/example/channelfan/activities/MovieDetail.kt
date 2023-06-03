package com.example.channelfan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.R
import com.example.channelfan.adapters.ReviewsAdapter
import com.example.channelfan.databinding.ActivityViewReviewBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.models.ClassReseña
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetail : AppCompatActivity() {
    var pelicula = ClassPelicula(null ,"","","",null,"","","","","","")
    private var listaReviews = arrayListOf<ClassReseña>()

    lateinit var binding: ActivityViewReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        obtenerPelicula()


        //Escribir Reseña
        val btn_CrearReseña = findViewById<ImageView>(R.id.btn_crearReview)
        btn_CrearReseña.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MovieDetail, Review::class.java)
                intent.putExtra("idPelicula", pelicula.id)
                startActivity(intent)
            }
        })



        //Action Bar
        val btn_home = findViewById<ImageView>(R.id.btn_inicio)
        btn_home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MovieDetail, Home::class.java)
                startActivity(intent)
            }
        })

        val btn_favorites = findViewById<ImageView>(R.id.btn_corazon)
        btn_favorites.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MovieDetail, Favorites::class.java)
                startActivity(intent)
            }
        })

        val btn_Search = findViewById<ImageView>(R.id.btn_search)
        btn_Search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MovieDetail, Search::class.java)
                startActivity(intent)
            }
        })

        val btn_profile = findViewById<ImageView>(R.id.btn_perfil)
        btn_profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MovieDetail, Profile::class.java)
                startActivity(intent)
            }
        })
    }




    fun obtenerPelicula(){

        CoroutineScope(Dispatchers.IO).launch {
            val idPelicula = intent.getStringExtra("idPelicula")
            val call = RetrofitClient.MOVIE_WEB_SERVICE.obtenerPelicula(idPelicula.toString())
            runOnUiThread {
                if (call.isSuccessful) {
                    pelicula = call.body()!!



                    binding.tvTitulo.text = "Titulo: ${pelicula.titulo}"
                    binding.tvAnio.text = "Año: ${pelicula.año}"
                    binding.tvDirector.text = "Director: ${pelicula.director}"
                    binding.tvSinopsis.text = "Sinopsis: ${pelicula.sinopsis}"
                    binding.tvDuracion.text = "Duración: ${pelicula.duracion}"
                    binding.tvFechaEstreno.text = "Fecha de Estreno: ${pelicula.fechaEstreno}"

                    val filmPhoto = binding.ivFilmReview
                    Glide.with(filmPhoto.context).load(pelicula.imagen).into(filmPhoto)

                    obtenerReseñas()
                } else {
                    Toast.makeText(this@MovieDetail, "ERROR Obtener Pelicula", Toast.LENGTH_SHORT)

                }
            }
        }
    }

    private fun initRecyclerViewReviews(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerReviews)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = ReviewsAdapter(listaReviews)

    }


    fun obtenerReseñas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.REVIEW_WEB_SERVICE.obtenerReseñasPelicula(pelicula.id.toString())
            runOnUiThread{
                if (call.isSuccessful){
                    listaReviews = call.body()!!.listaReseñas
                    initRecyclerViewReviews()
                }else {
                    Toast.makeText(this@MovieDetail,"ERROR CONSULTAR,TODOS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}