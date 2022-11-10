package com.juang.smallfeet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.navigation.fragment.findNavController

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.juang.smallfeet.view.adapter.CatalogoAdapter


class CatalogoFragment : Fragment() {

    lateinit var recyclerCat:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_catalogo, container, false)
        recyclerCat=view.findViewById(R.id.recyclerview)
        val adapter=CatalogoAdapter()
        recyclerCat.layoutManager=LinearLayoutManager(context)
        recyclerCat.adapter=adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btm= view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_catalogoFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_catalogoFragment_to_cuentaFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_catalogoFragment_to_ubicacionFragment)
        }
    }
    }

}