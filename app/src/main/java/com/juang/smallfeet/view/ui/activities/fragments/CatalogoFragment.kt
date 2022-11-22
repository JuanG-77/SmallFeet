package com.juang.smallfeet.view.ui.activities.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.juang.smallfeet.R
import com.juang.smallfeet.model.zapatos
import com.juang.smallfeet.view.adapter.CatalogoAdapter
import com.juang.smallfeet.view.adapter.OnBookItemClickListener
import com.juang.smallfeet.viewmodel.CatalogoViewModel


class CatalogoFragment : Fragment(), OnBookItemClickListener {

    lateinit var recyclerCat:RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: CatalogoAdapter
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
    private val viewmodel by lazy { ViewModelProvider(this).get(CatalogoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_catalogo, container, false)
        recyclerCat=view.findViewById(R.id.recyclerview)
        adapter=CatalogoAdapter(requireContext(), this)
        recyclerCat.layoutManager=LinearLayoutManager(context)
        recyclerCat.adapter=adapter
        observeData()
        return view

    }

    fun observeData(){
        viewmodel.catalogoData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btm= view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_catalogoFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_catalogoFragment_to_cuentaFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_catalogoFragment_to_ubicacionFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_catalogoFragment_to_loginActivity)
                    true
                }
            }
        }
    }

    override fun onItemClick(zapato: zapatos, position: Int) {
        val titulo:String=zapato.titulo
        val precio:String=zapato.precio
        val tallas:String=zapato.tallas
        val imagen:String=zapato.imagen
        val dato= hashMapOf(
            "titulo" to titulo,
            "precio" to precio,
            "tallas" to tallas,
            "imagen" to imagen
        )
        database.collection("compras")
            .document(titulo)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context,"el producto fue añadido al carrito de compras", Toast.LENGTH_SHORT).show()
            }
    }
}