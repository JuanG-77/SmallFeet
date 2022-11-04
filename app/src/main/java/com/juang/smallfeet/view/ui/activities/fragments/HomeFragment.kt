package com.juang.smallfeet.view.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.juang.smallfeet.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardCat = view.findViewById<ImageView>(R.id.cardCatalogo)
        cardCat.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_catalogoFragment)
        }
        val cardCom = view.findViewById<ImageView>(R.id.cardCompras)
        cardCom.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_comprasFragment)
        }
        val cardUbi = view.findViewById<ImageView>(R.id.cardUbicacion)
        cardUbi.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_ubicacionFragment)
        }
        val cardPerfil = view.findViewById<ImageView>(R.id.cardCuenta)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cuentaFragment)
        }

    }
}