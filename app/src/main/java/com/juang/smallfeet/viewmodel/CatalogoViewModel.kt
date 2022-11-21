package com.juang.smallfeet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juang.smallfeet.model.zapatos
import com.juang.smallfeet.repositorio.repo

class CatalogoViewModel:ViewModel(){
    val repo = repo()

    fun catalogoData():LiveData<MutableList<zapatos>>{
        val mutabledata = MutableLiveData<MutableList<zapatos>>()
        repo.getcatalogoData().observeForever { result ->
            mutabledata.value = result
        }
        return mutabledata
    }
}