package com.juang.smallfeet.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.juang.smallfeet.R

class LoginActivity : AppCompatActivity() {
    lateinit var inicioBotton:Button
    lateinit var registroBotton:Button
    lateinit var recuperarBotton:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inicioBotton=findViewById(R.id.btinicio)
        registroBotton=findViewById(R.id.btregistro)
        recuperarBotton=findViewById(R.id.btrecuperar)

        inicioBotton.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        registroBotton.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        recuperarBotton.setOnClickListener{
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }
}