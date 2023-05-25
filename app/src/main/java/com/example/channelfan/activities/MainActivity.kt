package com.example.channelfan.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassUsuario
import com.example.channelfan.R
import com.example.channelfan.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var usuario = ClassUsuario(null ,"","","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Hide Toolbar
        supportActionBar?.hide()




        // Ir al registro
        val btn_Register = findViewById<Button>(R.id.btn_Register)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, RegisterUser::class.java)
                startActivity(intent)
            }
        })

        // Iniciar Sesion
        val btn_Home = findViewById<Button>(R.id.btn_Home)
        btn_Home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var isValido = validarCampos()
                if (isValido){
                    iniciarSesion()
                }
                else{
                    Toast.makeText(this@MainActivity,"Faltan llenar campos",Toast.LENGTH_SHORT).show()
                    return
                }
            }
        })


    }



    fun iniciarSesion() {
        this.usuario.email = binding.editTextTextPersonName.text.toString()
        this.usuario.password = binding.editTextTextPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.iniciarSesion(usuario)
            if (call.isSuccessful) {
                val idUsuario = call.body()!!
                LimpiarCampos()
                LimpiarObjeto()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Login Exitoso", Toast.LENGTH_SHORT).show()
                }
                // Obtén una referencia al objeto SharedPreferences
                val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)

                // Guarda el idUsuario en SharedPreferences
                sharedPreferences.edit().putString("idUsuario", idUsuario).apply()
                val intent = Intent(this@MainActivity, Home::class.java)
                startActivity(intent)
            } else {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "No existen usuarios con estas credenciales", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }



    fun validarCampos(): Boolean{
        return !(binding.editTextTextPersonName.text.isNullOrEmpty() || binding.editTextTextPassword.text.isNullOrEmpty())
    }
    fun LimpiarCampos(){
        binding.editTextTextPersonName.setText("")
        binding.editTextTextPassword.setText("")

    }
    fun LimpiarObjeto(){
        this.usuario.email = ""
        this.usuario.password = ""
    }
}
