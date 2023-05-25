package com.example.channelfan.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.channelfan.R
import android.widget.Spinner
import android.widget.Toast
import com.example.channelfan.databinding.ActivityRegisterMovieBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterMovie : AppCompatActivity() {

    lateinit var binding: ActivityRegisterMovieBinding
    var pelicula = ClassPelicula(null ,"","","",null,"","","","","","")

    private var spClasifiacion:Spinner?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide Toolbar
        supportActionBar?.hide()


        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@RegisterMovie,"Error 404, sesión expirada NO USER ID FOUND", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@RegisterMovie, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d("Login", idUsuario.toString())

        //SPINNER
        spClasifiacion = findViewById(R.id.spClasificacion)
        val listaClasifiaciones = arrayOf("Seleccione una Clasificacion", "AA", "A", "B", "B15", "C", "D")
        var adaptador:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaClasifiaciones)
        spClasifiacion?.adapter = adaptador


        //Cancel
        val btn_Cancel = findViewById<Button>(R.id.btn_CancelPeli)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@RegisterMovie, Profile::class.java)
                startActivity(intent)
            }
        })


        //Register
        val btn_Register = findViewById<Button>(R.id.btn_CrearPeli)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var isValido = validarCampos()
                if (isValido) {
                    agregarPelicula()
                } else {
                    Toast.makeText(this@RegisterMovie, "Faltan llenar campos", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
            }
        })


    }



    fun validarCampos(): Boolean{
        return !(binding.edPeliTitulo.text.isNullOrEmpty() || binding.edFecha.text.isNullOrEmpty()|| binding.edDirector.text.isNullOrEmpty()|| binding.tvClasificacion.text.isNullOrEmpty())
    }
    fun  agregarPelicula(){
        this.pelicula.titulo = binding.edPeliTitulo.text.toString()
        this.pelicula.año =  binding.edFecha.text.toString()
        this.pelicula.director = binding.edDirector.text.toString()
        this.pelicula.generos =null
        this.pelicula.clasificacion = binding.tvClasificacion.text.toString()
        this.pelicula.sinopsis = binding.edSinopsis.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.MOVIE_WEB_SERVICE.agregarPelicula(pelicula)
            if (call.isSuccessful){
                runOnUiThread {
                    Toast.makeText(this@RegisterMovie, "Registro Exitoso", Toast.LENGTH_SHORT)
                        .show()
                }
                LimpiarCampos()
                LimpiarObjeto()
            }else{
                runOnUiThread {
                    Toast.makeText(this@RegisterMovie, "ERROR Registro", Toast.LENGTH_SHORT)
                }
            }
        }


    }
    fun LimpiarCampos(){
        binding.edPeliTitulo.setText("")
        binding.edFecha.setText("")
        binding.edDirector.setText("")
        binding.edSinopsis.setText("")
        binding.tvClasificacion.setText("")

    }
    fun LimpiarObjeto(){
        this.pelicula.titulo = ""
        this.pelicula.año = ""
        this.pelicula.director = ""
        this.pelicula.generos =null
        this.pelicula.clasificacion = ""
        this.pelicula.sinopsis = ""
    }
}