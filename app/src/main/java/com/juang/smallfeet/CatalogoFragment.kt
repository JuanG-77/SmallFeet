package com.juang.smallfeet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.navigation.fragment.findNavController

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView



class CatalogoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalogo, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_catalogoFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_catalogoFragment_to_cuentaFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_catalogoFragment_to_ubicacionFragment)
            }
        }
    }


        val cardNina1 = view.findViewById<ImageView>(R.id.cardNinaelegante)
        cardNina1.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_nina1Fragment)
        }
        val cardNina2 = view.findViewById<ImageView>(R.id.cardNinaTenis)
        cardNina2.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_nina2Fragment)
        }
        val cardNina3 = view.findViewById<ImageView>(R.id.cardNinatenisdos)
        cardNina3.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_nina3Fragment)
        }
        val cardNino1 = view.findViewById<ImageView>(R.id.cardNinoElegante)
        cardNino1.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_nino1Fragment)
        }
        val cardNino2 = view.findViewById<ImageView>(R.id.cardNinoBotas)
        cardNino2.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_nino2Fragment)
        }
        val cardNino3 = view.findViewById<ImageView>(R.id.cardNinoTenis)
        cardNino3.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_nino3Fragment)
        }

    }
}