package com.juang.smallfeet.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.juang.smallfeet.R

class LoginActivity : AppCompatActivity() {
    lateinit var inicioBotton:Button
    lateinit var registroBotton:Button
    lateinit var recuperarBotton:TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = Firebase.auth

        inicioBotton=findViewById(R.id.btinicio)
        registroBotton=findViewById(R.id.btregistro)
        recuperarBotton=findViewById(R.id.btrecuperar)

        val correo = findViewById<EditText>(R.id.tvemail)
        val contrasena = findViewById<EditText>(R.id.tvclave)

        inicioBotton.setOnClickListener{
            login(correo.text.toString(), contrasena.text.toString())
        }

        registroBotton.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        recuperarBotton.setOnClickListener{
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }

    private fun login(correo: String, contrasena: String){
        firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
                task -> if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                else{
                    Toast.makeText(baseContext, "Error de autenticación", Toast.LENGTH_SHORT).show()
                }
            }
    }
}