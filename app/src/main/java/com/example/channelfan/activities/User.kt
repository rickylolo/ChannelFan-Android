package com.example.channelfan.activities

import android.content.Context
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

class User : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    var usuario = ClassUsuario(null ,"","","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Hide Toolbar
        supportActionBar?.hide()

        val isEditando = intent.getBooleanExtra("isEditando", false)
        //Cancel
        val btn_Cancel = findViewById<Button>(R.id.btn_Cancel)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if(isEditando){
                    val intent = Intent(this@User, Profile::class.java)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this@User, MainActivity::class.java)
                    startActivity(intent)
                }

            }
        })


        if(isEditando){
            // Obtén una referencia al objeto SharedPreferences
            val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)
            // Obtén el idUsuario almacenado en SharedPreferences
            val idUsuario = sharedPreferences.getString("idUsuario", "")
            obtenerUser(idUsuario.toString())
            val buttonActualizar = findViewById<Button>(R.id.btn_Register)
            // Cambiar el texto del botón
            buttonActualizar.text = "Actualizar Usuario"

        }else{
            val buttonRegistrar = findViewById<Button>(R.id.btn_Register)
            // Cambiar el texto del botón
            buttonRegistrar.text = "Registrar Usuario"

        }

        //Register
        val btn_Register = findViewById<Button>(R.id.btn_Register)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var isValido = false
                if(isEditando){
                    isValido = validarCamposActualizar()
                }
                else
                {
                    isValido = validarCampos()
                }

                if (!isValido) {
                    runOnUiThread {
                        Toast.makeText(this@User,"Faltan llenar campos",Toast.LENGTH_SHORT).show()
                    }
                    return
                }

                var repeatPassword = binding.editTextRepeatpassword.text.toString()
                var password =  binding.editTextPassword.text.toString()
                if(repeatPassword != password){
                        runOnUiThread {
                            Toast.makeText(this@User,"La contraseña debe coincidir",Toast.LENGTH_SHORT).show()
                        }
                        return
                }
                if(isEditando){
                    editarUsuario()
                }
                else{
                    agregarUsuario()
                }



            }
        })
    }


    fun validarCampos(): Boolean{
        return !(binding.editTextName.text.isNullOrEmpty() || binding.editTextSurname.text.isNullOrEmpty()
                || binding.editTextEmail.text.isNullOrEmpty() || binding.editTextPassword.text.isNullOrEmpty()
                || binding.editTextRepeatpassword.text.isNullOrEmpty() || binding.editTextAddress.text.isNullOrEmpty())
    }

    fun validarCamposActualizar(): Boolean{
        return !(binding.editTextName.text.isNullOrEmpty() || binding.editTextSurname.text.isNullOrEmpty()
                || binding.editTextEmail.text.isNullOrEmpty() || binding.editTextAddress.text.isNullOrEmpty())
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
                runOnUiThread {
                    Toast.makeText(this@User, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                }
                LimpiarCampos()
                LimpiarObjeto()
                val intent = Intent(this@User, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                runOnUiThread {
                    Toast.makeText(this@User, "Ya existen usuarios con esas credenciales", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun  editarUsuario(){
        this.usuario.firstName = binding.editTextName.text.toString()
        this.usuario.lastName = binding.editTextSurname.text.toString()
        this.usuario.email = binding.editTextEmail.text.toString()
        this.usuario.password = binding.editTextPassword.text.toString()
        this.usuario.address = binding.editTextAddress.text.toString()

        this.usuario.avatar = "1"

        // Obtén una referencia al objeto SharedPreferences
        val sharedPreferences = getSharedPreferences("Sesion", Context.MODE_PRIVATE)
        // Obtén el idUsuario almacenado en SharedPreferences
        val idUsuario = sharedPreferences.getString("idUsuario", "")

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.actualizarUsuario(idUsuario.toString(),usuario)
            if (call.isSuccessful){
                runOnUiThread {
                    Toast.makeText(this@User, "Usuario Actualizado Correctamente", Toast.LENGTH_SHORT).show()
                }
                LimpiarCampos()
                LimpiarObjeto()
                val intent = Intent(this@User, Profile::class.java)
                startActivity(intent)
            }
            else{
                runOnUiThread {
                    Toast.makeText(this@User, "Ya existen usuarios con esas credenciales", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }



    private fun obtenerUser(idUser : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.USER_WEB_SERVICE.obtenerUsuario(idUser)
            runOnUiThread{
                if (call.isSuccessful){
                    usuario = call.body()!!
                    binding.editTextName.setText(usuario.firstName)
                    binding.editTextSurname.setText(usuario.lastName)
                    binding.editTextEmail.setText(usuario.email)
                    binding.editTextAddress.setText(usuario.address)


                }else {
                    Toast.makeText(this@User,"ERROR CONSULTAR USUARIO", Toast.LENGTH_SHORT).show()
                }
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