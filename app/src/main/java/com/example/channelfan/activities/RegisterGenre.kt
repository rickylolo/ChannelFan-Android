package com.example.channelfan.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassGenero
import com.example.channelfan.R
import com.example.channelfan.databinding.ActivityRegisterGenreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterGenre : AppCompatActivity() {

    lateinit var binding: ActivityRegisterGenreBinding
    var genero = ClassGenero(null ,"","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityRegisterGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide Toolbar
        supportActionBar?.hide()

        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        // Verifica si el idUsuario es válido
        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@RegisterGenre,"Error 404, sesión expirada NO USER ID FOUND", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@RegisterGenre, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d("Login", idUsuario.toString())



        //Cancel
        val btn_Cancel = findViewById<Button>(R.id.btn_CancelGen)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@RegisterGenre, Profile::class.java)
                startActivity(intent)
            }
        })

        //Register Genre
        val btn_Register = findViewById<Button>(R.id.btn_CrearGen)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var isValido = validarCampos()

                if (isValido) {
                    agregarGenero()
                } else {
                    Toast.makeText(this@RegisterGenre, "Faltan llenar campos", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
            }
        })

    }

    fun validarCampos(): Boolean{
        return !(binding.ednameGenero.text.isNullOrEmpty() || binding.eddescGenero.text.isNullOrEmpty())
    }
    fun  agregarGenero(){
        this.genero.nombre = binding.ednameGenero.text.toString()
        this.genero.descripcion = binding.eddescGenero.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.GENRE_WEB_SERVICE.agregarGenero(genero)
            if (call.isSuccessful){
                runOnUiThread {
                    Toast.makeText(this@RegisterGenre, "Registro Exitoso", Toast.LENGTH_SHORT)
                        .show()
                }
                LimpiarCampos()
                LimpiarObjeto()
            }else{
                runOnUiThread {
                    Toast.makeText(this@RegisterGenre, "ERROR Registro", Toast.LENGTH_SHORT)
                }
            }
        }


    }
    fun LimpiarCampos(){
        binding.ednameGenero.setText("")
        binding.eddescGenero.setText("")

    }
    fun LimpiarObjeto(){
        this.genero.nombre = ""
        this.genero.descripcion = ""
    }
}



