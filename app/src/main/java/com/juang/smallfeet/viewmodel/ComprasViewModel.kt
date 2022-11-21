package com.juang.smallfeet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juang.smallfeet.model.compras
import com.juang.smallfeet.repositorio.repo
import androidx.lifecycle.ViewModel

class ComprasViewModel:ViewModel(){
    val repo=repo()
    fun fetchComprasData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        repo.getComprasData().observeForever {
            mutableData.value=it
        }
        return mutableData
    }
}