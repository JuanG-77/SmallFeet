package com.juang.smallfeet.view.ui.activities.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.juang.smallfeet.model.compras
import com.juang.smallfeet.view.adapter.ComprasAdapter
import com.juang.smallfeet.view.adapter.OnCompraItemClickListener
import com.juang.smallfeet.viewmodel.ComprasViewModel


class ComprasFragment : Fragment(), OnCompraItemClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    lateinit var precioT:TextView
    lateinit var compraT:TextView
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
    private val viewModel by lazy {ViewModelProvider(this).get(ComprasViewModel::class.java)}

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView=view.findViewById(R.id.recyclerviewcompra)
        precioT=view.findViewById(R.id.preciototal)
        compraT=view.findViewById(R.id.realizar)
        adapter= ComprasAdapter(requireContext(),this)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observeData()
        preciototal()
        compraT.setOnClickListener {
            realizarcompra()
        }
        return view
    }

    private fun observeData() {
        viewModel.fetchComprasData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun preciototal(){
        database.collection("compras")
            .get()
            .addOnSuccessListener {
                result->
                val preciounitario= mutableListOf<String>()
                for(document in result){
                    val precio=document["precio"].toString()
                    preciounitario.add(precio!!)
                }
                val preciototal=preciounitario.mapNotNull { it.toIntOrNull() }.sum()
                precioT.setText(Integer.toString(preciototal))
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_comprasFragment_to_cuentaFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_comprasFragment_to_ubicacionFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_catalogoFragment_to_loginActivity)
                    true
                }
            }
        }
    }
    private fun realizarcompra() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("CompraSmallFeet")
        builder.setMessage("¿Finalizar la compra?")
        builder.setPositiveButton("Aceptar"){
            dialog,which->
            findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
        }
        builder.setNegativeButton("Cancelar pago",null)
        builder.show()
    }

    override fun onItemClick(zatato: compras, position: Int) {
        database.collection("compras")
            .document(zatato.titulo)
            .delete()
    }
}