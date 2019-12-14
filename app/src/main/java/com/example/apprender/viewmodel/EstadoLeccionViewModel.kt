package com.example.apprender.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apprender.domain.FirestoreUseCase

class EstadoLeccionViewModel: ViewModel() {

    private val firestoreUseCase = FirestoreUseCase()

    fun getLeccionUsuario(rut: String, capitulo: String, posicion: Int){
        firestoreUseCase.getLeccionFirestore(rut, capitulo, posicion)
    }

    fun getEstadoForChapter(rut: String, capitulo: String, leccion: String, posicion: Int){
        firestoreUseCase.getEstadoForChapter(rut, capitulo, leccion, posicion)
    }

    fun fetchEstadoLeccion(): LiveData<MutableList<String>>{
        val data = MutableLiveData<MutableList<String>>()
        firestoreUseCase.getResultEstadoLeccion().observeForever {
            data.value = it
        }
        return data
    }
}