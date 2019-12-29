package com.apprenderchile.apprender.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apprenderchile.apprender.data.FirebaseRepo
import com.apprenderchile.apprender.domain.FirestoreUseCase
import com.apprenderchile.apprender.view.supportClasses.LeccionOneProfile

class EstadoLeccionViewModel: ViewModel() {

    private val firestoreUseCase = FirestoreUseCase()
    private val repo = FirebaseRepo()
    fun getLeccionUsuario(rut: String, capitulo: String, posicion: Int){
        firestoreUseCase.getLeccionFirestore(rut, capitulo, posicion)
    }

    fun getEstadoForChapter(rut: String, capitulo: String, leccion: String, posicion: Int){
        firestoreUseCase.getEstadoForChapter(rut, capitulo, leccion, posicion)
    }

    fun getPuntajeUsuario(rut: String){
        firestoreUseCase.getPuntajeLeccion(rut)
    }

    fun fetchEstadoLeccion(): LiveData<MutableList<String>>{
        val data = MutableLiveData<MutableList<String>>()
        firestoreUseCase.getResultEstadoLeccion().observeForever {
            data.value = it
        }
        return data
    }

    fun fetchPuntajeLeccion(rut: String): LiveData<MutableList<LeccionOneProfile>>{
        val data = MutableLiveData<MutableList<LeccionOneProfile>>()
        repo.getPuntaje(rut).observeForever {
            data.value = it
        }
        return data
    }
}