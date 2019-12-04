package com.example.apprender.domain

import androidx.lifecycle.LiveData
import com.example.apprender.data.FirebaseRepo
import com.google.firebase.Timestamp

class FirestoreUseCase {

    private val firebaseRepo = FirebaseRepo()

    fun setUserFirestore(nombre: String, apellido: String, edad: Int, rut: Int, genero: String, fecha: Timestamp){
        firebaseRepo.setUserDataBase(nombre, apellido, edad, rut, genero, fecha)
    }

    fun loginUser(rut: String){
        firebaseRepo.loginUser(rut)
    }

    fun getResult(): LiveData<Boolean>{
        return firebaseRepo.getDataResult()
    }

    fun getResultLogin(): LiveData<MutableList<String>>{
        return firebaseRepo.getLoginData()
    }
}