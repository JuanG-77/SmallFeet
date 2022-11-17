package com.juang.smallfeet

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class UbicacionFragment : Fragment(), OnMapReadyCallback {

    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ubicacion, container, false)
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_ubicacionFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_ubicacionFragment_to_cuentaFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_catalogoFragment_to_loginActivity)
                    true
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        val colombia = LatLng(4.81333, -75.69611)
        map?.let{
            this.googleMap = it
            map.addMarker(MarkerOptions().position(colombia))
        }
        enableLocation()
    }

    private fun isLocationPermissionGrated() = ContextCompat.checkSelfPermission(this.requireContext(),
    android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if(!::googleMap.isInitialized)return
        if (isLocationPermissionGrated()){
            googleMap.isMyLocationEnabled = true
        }
        else{
            requestLocationPermission()
        }
    }

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    private fun requestLocationPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this.context, "Requiere activar en ajustes", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(this.requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION ->
                if (grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    googleMap.isMyLocationEnabled=true
                }
                else{
                    Toast.makeText(context, "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT).show()
                }
                else ->{}
        }
    }
}