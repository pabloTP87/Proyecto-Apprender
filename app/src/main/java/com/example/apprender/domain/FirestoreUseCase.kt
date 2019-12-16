package com.example.apprender.domain

import androidx.lifecycle.LiveData
import com.example.apprender.data.FirebaseRepo
import com.google.firebase.Timestamp

class FirestoreUseCase {

    private val firebaseRepo = FirebaseRepo()
    // envía datos para guardar una Usuario en firebaseRepo
    fun setUserFirestore(nombre: String, apellido: String, edad: Int, rut: Int, genero: String, fecha: Timestamp){
        firebaseRepo.setUserDataBase(nombre, apellido, edad, rut, genero, fecha)
    }
    // envía datos para guardar una lección en firebaseRepo
    fun setLeccionFirestore(capitulo: String, numLeccion: String,nomLeccion: String, puntaje: Int, tiempo: Int, correctas: Int, incorrectas: Int, estado: String, rut: String){
        firebaseRepo.setLeccionDataBase(capitulo, numLeccion, nomLeccion, puntaje, tiempo, correctas, incorrectas, estado, rut)
    }
    // envía datos para guardar una encuesta en firebaseRepo
    fun setEncuestaFirestore(respuesta1: String, respuesta2: String, respuesta3: String, rut: String){
        firebaseRepo.setEncuestaDataBase(respuesta1, respuesta2, respuesta3, rut)
    }
    // envía datos para actualizar el estado de una lección en firebaseRepo
    fun actualizarEstado(rut: String, capitulo: String, leccion: String, estado: String){
        firebaseRepo.updateEstadoLeccion(rut, capitulo, leccion, estado)
    }

    fun getLeccionFirestore(rut: String, capitulo: String, posicion: Int){
        firebaseRepo.getLeccion(rut, capitulo, posicion)
    }

    fun getEstadoForChapter(rut: String, capitulo: String, leccion: String, posicion: Int){
        firebaseRepo.getStateLeccionForChapter(rut, capitulo, leccion, posicion)
    }

    fun getPuntajeLeccion(rut: String){
        firebaseRepo.getPuntaje(rut)
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

    fun getResultEstadoLeccion(): LiveData<MutableList<String>>{
        return firebaseRepo.getEstadoLeccion()
    }
}