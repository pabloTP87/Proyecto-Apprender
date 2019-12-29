package com.apprenderchile.apprender.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apprenderchile.apprender.domain.FirestoreUseCase
import com.google.firebase.Timestamp

class FirestoreViewModel : ViewModel() {

   private val firestoreUseCase = FirestoreUseCase()

    fun crearUsuario(nombre: String, apellido: String, edad: Int, rut: Int, genero: String, fecha: Timestamp){
        firestoreUseCase.setUserFirestore(nombre, apellido, edad, rut, genero, fecha)
    }

    fun saveLeccionData(capitulo: String, numLeccion: String,nomLeccion: String, puntaje: Int, tiempo: Int, correctas: Int, incorrectas: Int, estado: String, rut: String){
        firestoreUseCase.setLeccionFirestore(capitulo, numLeccion, nomLeccion, puntaje, tiempo, correctas, incorrectas, estado, rut)
    }

    fun saveEncuestaData(respuesta1: String, respuesta2: String, respuesta3: String, rut: String){
        firestoreUseCase.setEncuestaFirestore(respuesta1, respuesta2, respuesta3, rut)
    }

    fun validarUsuario(rut: String){
        firestoreUseCase.loginUser(rut)
    }

    fun actualizarEstadoLeccion(rut: String, capitulo: String, leccion: String, estado: String){
        firestoreUseCase.actualizarEstado(rut, capitulo, leccion, estado)
    }

    fun fetchDataComplition(): LiveData<Boolean>{
        val data = MutableLiveData<Boolean>()
        firestoreUseCase.getResult().observeForever {
            data.value = it
        }
        return data
    }

    fun fetchLoginData(): LiveData<MutableList<String>>{
        val data = MutableLiveData<MutableList<String>>()
        firestoreUseCase.getResultLogin().observeForever {
            data.value = it
        }
        return data
    }
}