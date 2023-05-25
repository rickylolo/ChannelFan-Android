package com.example.channelfan.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.channelfan.R
import com.example.channelfan.databinding.ActivityRegisterGenreBinding
import com.example.channelfan.databinding.ActivityRegisterReviewBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassGenero
import com.example.channelfan.models.ClassReseña
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterReview : AppCompatActivity() {

    lateinit var binding: ActivityRegisterReviewBinding
    var review = ClassReseña(null ,null,null,"","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide Toolbar
        supportActionBar?.hide()


        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@RegisterReview,"Error 404, sesión expirada NO USER ID FOUND", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@RegisterReview, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d("Login", idUsuario.toString())

        //Cancel
        val btn_Cancel = findViewById<Button>(R.id.btn_CancelReview)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@RegisterReview, Profile::class.java)
                startActivity(intent)
            }
        })

        //Register
        val btn_Register = findViewById<Button>(R.id.btnCrearReview)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var isValido = validarCampos()
                if (isValido) {
                    agregarReview()
                } else {
                    Toast.makeText(this@RegisterReview, "Faltan llenar campos", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
            }
        })
    }



    fun validarCampos(): Boolean{
        return !(binding.edTitulo.text.isNullOrEmpty() || binding.edDescripcion.text.isNullOrEmpty())
    }
    fun  agregarReview(){
        this.review.peliculas = null
        this.review.usuario = null

        this.review.titulo = binding.edTitulo.text.toString()
        this.review.descripcion = binding.edDescripcion.text.toString()
        this.review.calificacion = binding.tvCalificacion.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.REVIEW_WEB_SERVICE.agregarReseña(review)
            if (call.isSuccessful){
                runOnUiThread {
                    Toast.makeText(this@RegisterReview, "Registro Exitoso", Toast.LENGTH_SHORT)
                        .show()
                }
                LimpiarCampos()
                LimpiarObjeto()
            }else{
                runOnUiThread {
                    Toast.makeText(this@RegisterReview, "ERROR Registro", Toast.LENGTH_SHORT)
                }
            }
        }


    }
    fun LimpiarCampos(){
        binding.edTitulo.setText("")
        binding.edDescripcion.setText("")
        binding.tvCalificacion.setText("")

    }
    fun LimpiarObjeto(){
        this.review.peliculas = null
        this.review.usuario = null
        this.review.titulo = ""
        this.review.descripcion = ""
        this.review.calificacion = ""
        this.review.fecha = ""
    }
}