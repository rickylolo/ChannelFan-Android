package com.example.channelfan.activities

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.channelfan.R
import com.example.channelfan.databinding.ActivityRegisterMovieBinding
import com.example.channelfan.endpoints.RetrofitClient
import com.example.channelfan.models.ClassPelicula
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RegisterMovie : AppCompatActivity() {

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
        if(uri!=null){
            //imagen seleccionada
                ivImg.setImageURI(uri)
            Log.i("aris","Seleccion")
        }else{
            Log.i("aris","No Seleccionado")
            //no imagen
        }
    }
    lateinit var ivImg : ImageView
    lateinit var binding: ActivityRegisterMovieBinding
    var pelicula = ClassPelicula(null ,"","","",null,"","","","","","")

    private var spClasifiacion:Spinner?=null
    private var cajaFecha:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cajaFecha = findViewById(R.id.edFecha)
        ivImg = findViewById(R.id.imgPelicula)

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

        val btnFecha = findViewById<Button>(R.id.btnFecha)
        btnFecha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fecha = DatePickerFragment {year, month, dayOfMonth -> showResults(year, month, dayOfMonth) }
                fecha.show(supportFragmentManager,"DatePicker")
            }
        })

        val btnImagen = findViewById<Button>(R.id.btnAddImg)
        btnImagen.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                /* ESTE SIRVE PARA LOS GIF
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType("img/gif")))*/
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        })

    }

    private fun showResults(year: Int, month: Int, dayOfMonth: Int) {
        var mes:String?=null
        var dia:String?=null
        var numint: Int?=0
        if (dayOfMonth<=9){
            dia = "0$dayOfMonth"
        }else
        {
            dia = dayOfMonth.toString()
        }

            if (month<=8){
                numint = month + 1
                mes = "0$numint"
            }else
            {
                numint = month + 1
                mes = numint.toString()
            }
            cajaFecha?.setText("$year/$mes/$dia")
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

    class DatePickerFragment (val listener:(year: Int, month: Int, dayOfMonth: Int)->Unit): DialogFragment(), DatePickerDialog.OnDateSetListener{

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c= Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(requireActivity(),this,year, month, dayOfMonth)
        }

        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            listener(year, month, dayOfMonth)
        }

    }
}