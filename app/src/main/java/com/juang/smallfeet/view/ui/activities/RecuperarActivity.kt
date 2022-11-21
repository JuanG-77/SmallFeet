package com.juang.smallfeet.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.juang.smallfeet.R

class RecuperarActivity : AppCompatActivity() {
    lateinit var restaurarbutton: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        firebaseAuth = Firebase.auth
        restaurarbutton=findViewById(R.id.btrestaurar)
        val correo = findViewById<EditText>(R.id.etcorreorecuperar)

        restaurarbutton.setOnClickListener{
            cambiocontrasena(correo.text.toString())
        }
    }

    private fun cambiocontrasena(correo:String){
        firebaseAuth.sendPasswordResetEmail(correo)
            .addOnCompleteListener(this){
                task -> if (task.isSuccessful){
                    Toast.makeText(baseContext, "Correo de cambio de contraseña enviado", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                else{
                    Toast.makeText(baseContext, "Correo invalido", Toast.LENGTH_SHORT).show()
                }
            }
    }
}