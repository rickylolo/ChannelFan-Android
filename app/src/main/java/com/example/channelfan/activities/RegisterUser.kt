package com.example.channelfan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.channelfan.databinding.ActivityRegisterBinding
import com.example.channelfan.models.ClassUsuario
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterUser : AppCompatActivity() {

    private var listaUsuarios = arrayListOf<ClassUsuario>()
    lateinit var binding: ActivityRegisterBinding
    var usuario = ClassUsuario(null ,"","","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Hide Toolbar
        supportActionBar?.hide()
        obtenerUsuarios()

        //Cancel
        val btn_Cancel = findViewById<Button>(R.id.btn_Cancel)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@RegisterUser, MainActivity::class.java)
                startActivity(intent)
            }
        })

        //Register
        val btn_Register = findViewById<Button>(R.id.btn_Register)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var isValido = validarCampos()

                if (isValido){
                    var repeatPassword = binding.editTextRepeatpassword.text.toString()
                    var password =  binding.editTextPassword.text.toString()
                    if(repeatPassword == password){
                        runOnUiThread {
                            Toast.makeText(
                                this@RegisterUser,
                                "La contraseña debe coincidir",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        return
                    }
                    agregarUsuario()
                }
                else{
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterUser,
                            "Faltan llenar campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return
                }
            }
        })
    }


    fun obtenerUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.obtenerUsuarios()
            runOnUiThread{
                if (call.isSuccessful){
                    listaUsuarios = call.body()!!.listaUsuarios
                    listaUsuarios?.forEach { usuario ->
                        Log.d("Usuarios", usuario.toString()) // Imprimir cada usuario en la consola
                    }
                }else {
                    Toast.makeText(this@RegisterUser,"ERROR CONSULTAR,TODOS",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun validarCampos(): Boolean{
        return !(binding.editTextName.text.isNullOrEmpty() || binding.editTextSurname.text.isNullOrEmpty()
                || binding.editTextEmail.text.isNullOrEmpty() || binding.editTextPassword.text.isNullOrEmpty()
                || binding.editTextRepeatpassword.text.isNullOrEmpty() || binding.editTextAddress.text.isNullOrEmpty())
    }
    fun  agregarUsuario(){
        this.usuario.firstName = binding.editTextName.text.toString()
        this.usuario.lastName = binding.editTextSurname.text.toString()
        this.usuario.email = binding.editTextEmail.text.toString()
        this.usuario.password = binding.editTextPassword.text.toString()
        this.usuario.address = binding.editTextAddress.text.toString()

        this.usuario.avatar = "1"
            //binding.etEmail.text.toString()
        this.usuario.userType = "1"


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.agregarUsuario(usuario)
            if (call.isSuccessful){
                Toast.makeText(this@RegisterUser,"Registro Exitoso",Toast.LENGTH_SHORT).show()
                LimpiarCampos()
                LimpiarObjeto()
            }

        }
    }
    fun LimpiarCampos(){
        binding.editTextName.setText("")
        binding.editTextSurname.setText("")
        binding.editTextEmail.setText("")
        binding.editTextPassword.setText("")
        binding.editTextRepeatpassword.setText("")
        binding.editTextAddress.setText("")
    }
    fun LimpiarObjeto(){
        this.usuario.firstName = ""
        this.usuario.lastName = ""
        this.usuario.email = ""
        this.usuario.password = ""
        this.usuario.address = ""
        this.usuario.avatar = ""
        this.usuario.userType = ""
    }



}