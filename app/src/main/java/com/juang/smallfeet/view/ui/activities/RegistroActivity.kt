package com.juang.smallfeet.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.juang.smallfeet.R
import java.lang.ref.Reference
import java.sql.DatabaseMetaData

class RegistroActivity : AppCompatActivity() {
    lateinit var registroBotton:Button
    private lateinit var nombre:EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth = Firebase.auth
        registroBotton = findViewById(R.id.btregistro)
        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("Usuario")
        nombre = findViewById((R.id.tvnombre))
        val correo = findViewById<EditText>(R.id.tvcorreo)
        val contrasena = findViewById<EditText>(R.id.tvcontrasena)

        registroBotton.setOnClickListener{
            crearcuenta(correo.text.toString(), contrasena.text.toString())
        }
    }

    private fun crearcuenta(correo:String, contrasena:String){
        val name:String = nombre.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
                Task -> if (Task.isSuccessful){
                    Toast.makeText(baseContext, "Cuenta creada con exito", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                else{
                    Toast.makeText(baseContext, "No se pudo crear su cuenta", Toast.LENGTH_SHORT).show()
                }
            }
    }
}