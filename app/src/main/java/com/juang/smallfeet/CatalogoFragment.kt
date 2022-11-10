package com.juang.smallfeet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton

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
        val nombrecompleto=view.findViewById<EditText>(R.id.Nombrecompleto)
        val correocompleto=view.findViewById<EditText>(R.id.Correocompleto)
        val direccioncompleto=view.findViewById<EditText>(R.id.Direccioncompleto)
        val telefonocompleto=view.findViewById<EditText>(R.id.Telefonocompleto)
        val btmeditnombre=view.findViewById<ImageButton>(R.id.nombreedit)
        val btmeditcorreo=view.findViewById<ImageButton>(R.id.correoedit)
        val btmeditdireccion=view.findViewById<ImageButton>(R.id.direccionedit)
        val btmedittelefono=view.findViewById<ImageButton>(R.id.telefononedit)
            nombrecompleto.isEnabled=false
            correocompleto.isEnabled=false
            direccioncompleto.isEnabled=false
            telefonocompleto.isEnabled=false
                btmeditnombre.setOnClickListener{
                    if(nombrecompleto.isEnabled==false){
                        nombrecompleto.isEnabled=true
                    } else if(nombrecompleto.isEnabled==true){
                        nombrecompleto.isEnabled=false
                    }
                }
                btmeditcorreo.setOnClickListener{
                    if(correocompleto.isEnabled==false){
                        correocompleto.isEnabled=true
                    } else if(correocompleto.isEnabled==true){
                        correocompleto.isEnabled=false
                    }
                }
                btmeditdireccion.setOnClickListener{
                    if(direccioncompleto.isEnabled==false){
                        direccioncompleto.isEnabled=true
                    } else if(direccioncompleto.isEnabled==true){
                        direccioncompleto.isEnabled=false
                    }
                }
                btmedittelefono.setOnClickListener{
                    if(telefonocompleto.isEnabled==false){
                        telefonocompleto.isEnabled=true
                    } else if(telefonocompleto.isEnabled==true){
                        telefonocompleto.isEnabled=false
                    }
                }

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