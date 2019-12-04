package com.example.apprender.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apprender.domain.FirestoreUseCase
import com.google.firebase.Timestamp

class FirestoreViewModel : ViewModel() {

   private val firestoreUseCase = FirestoreUseCase()

    fun crearUsuario(nombre: String, apellido: String, edad: Int, rut: Int, genero: String, fecha: Timestamp){
        firestoreUseCase.setUserFirestore(nombre, apellido, edad, rut, genero, fecha)
    }

    fun validarUsuario(rut: String){
        firestoreUseCase.loginUser(rut)
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