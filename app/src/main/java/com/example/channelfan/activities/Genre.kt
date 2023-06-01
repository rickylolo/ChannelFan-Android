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
import com.example.channelfan.databinding.ActivityGenreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Genre : AppCompatActivity() {

    lateinit var binding: ActivityGenreBinding
    var genero = ClassGenero(null ,"",null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide Toolbar
        supportActionBar?.hide()


        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        if (idUsuario.isNullOrEmpty()) {
            Toast.makeText(this@Genre,"Error 404, sesión expirada NO USER ID FOUND", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Genre, MainActivity::class.java)
            startActivity(intent)
        }


        val isEditando = intent.getBooleanExtra("isEditando", false)
        Log.d("Genre", "Valor de isEditando: $isEditando")
        if(isEditando){
            obtenerGenero()
            val buttonActualizar = findViewById<Button>(R.id.btn_CrearGen)
            // Cambiar el texto del botón
            buttonActualizar.text = "Actualizar Género"

        }else{
            val buttonRegistrar = findViewById<Button>(R.id.btn_CrearGen)
            // Cambiar el texto del botón
            buttonRegistrar.text = "Registrar Género"

        }

        //Cancel
        val btn_Cancel = findViewById<Button>(R.id.btn_CancelGen)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Genre, Admin::class.java)
                startActivity(intent)
            }
        })

        //Register Genre
        val btn_Register = findViewById<Button>(R.id.btn_CrearGen)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var isValido = validarCampos()

                if (!isValido) {
                    Toast.makeText(this@Genre, "Faltan llenar campos", Toast.LENGTH_SHORT)
                        .show()
                    return
                }


                if(!isEditando){
                    agregarGenero()
                }else{
                    editarGenero()
                }

                val intent = Intent(this@Genre, Admin::class.java)
                startActivity(intent)
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
                    Toast.makeText(this@Genre, "Registro Exitoso", Toast.LENGTH_SHORT)
                        .show()
                }
                LimpiarCampos()
                LimpiarObjeto()
            }else{
                runOnUiThread {
                    Toast.makeText(this@Genre, "ERROR Registro", Toast.LENGTH_SHORT)
                }
            }
        }


    }

    fun  editarGenero(){
        this.genero.nombre = binding.ednameGenero.text.toString()
        this.genero.descripcion = binding.eddescGenero.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val idGenero = intent.getStringExtra("idGenero")
            val call = RetrofitClient.GENRE_WEB_SERVICE.actualizarGenero(idGenero.toString(),genero)
            if (call.isSuccessful){
                runOnUiThread {
                    Toast.makeText(this@Genre, "Genero Actualizado Correctamente", Toast.LENGTH_SHORT)
                        .show()
                }
                LimpiarCampos()
                LimpiarObjeto()
            }else{
                runOnUiThread {
                    Toast.makeText(this@Genre, "ERROR Actualizar", Toast.LENGTH_SHORT)
                }
            }
        }


    }

    fun obtenerGenero(){

        CoroutineScope(Dispatchers.IO).launch {
            val idGenero = intent.getStringExtra("idGenero")
            val call = RetrofitClient.GENRE_WEB_SERVICE.obtenerGenero(idGenero.toString())
            runOnUiThread {
                if (call.isSuccessful) {
                    genero = call.body()!!
                    binding.ednameGenero.setText(genero.nombre)
                    binding.eddescGenero.setText(genero.descripcion)

                } else {
                    Toast.makeText(this@Genre, "ERROR Obtener Genero", Toast.LENGTH_SHORT)

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



