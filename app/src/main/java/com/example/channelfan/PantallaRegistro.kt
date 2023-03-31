package com.example.channelfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class PantallaRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Hide Toolbar
        supportActionBar?.hide()


        //Buttons to another activity
        val btn_Cancel = findViewById<Button>(R.id.btn_Cancel)
        btn_Cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@PantallaRegistro, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}