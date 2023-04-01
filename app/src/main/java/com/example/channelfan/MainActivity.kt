package com.example.channelfan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Hide Toolbar
        supportActionBar?.hide()

        //Buttons to another activity
        val btn_Register = findViewById<Button>(R.id.btn_Register)
        btn_Register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, PantallaRegistro::class.java)
                startActivity(intent)
            }
        })
        val btn_Home = findViewById<Button>(R.id.btn_Home)
        btn_Home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, Home::class.java)
                startActivity(intent)
            }
        })
    }
}
