package com.juang.smallfeet.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.MutableData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.juang.smallfeet.model.compras
import com.juang.smallfeet.model.zapatos

class repo {
    fun getcatalogoData():LiveData<MutableList<zapatos>>{
        val mutabledata = MutableLiveData<MutableList<zapatos>>()

        FirebaseFirestore.getInstance().collection("zapatos").get()
            .addOnSuccessListener { result ->
                val lisData = mutableListOf<zapatos>()
                for (document in result){
                    val titulo = document.getString("titulo")
                    val precio = document.getString("precio")
                    val tallas = document.getString("tallas")
                    val imagen = document.getString("imagen")
                    val zapato = zapatos(titulo!!, precio!!, tallas!!, imagen!!)
                    lisData.add(zapato)
                }
                mutabledata.value = lisData
            }
        return mutabledata
    }
    fun getComprasData():LiveData<MutableList<compras>> {
        val mutableData = MutableLiveData<MutableList<compras>>()
        FirebaseFirestore.getInstance().collection("compras")
            .get().addOnSuccessListener { result ->
                val listData = mutableListOf<compras>()
                for (document in result) {
                    val titulo = document.getString("titulo")
                    val precio = document.getString("precio")
                    val tallas = document.getString("tallas")
                    val imagen = document.getString("imagen")
                    val compra = compras(titulo!!, precio!!, tallas!!, imagen!!)
                    listData.add(compra)
                }
                mutableData.value=listData
            }
        return mutableData
    }
}