package com.juang.smallfeet.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.juang.smallfeet.R

class RecuperarActivity : AppCompatActivity() {
    lateinit var restaurarbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        restaurarbutton=findViewById(R.id.btrestaurar)
        restaurarbutton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}