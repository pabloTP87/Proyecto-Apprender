package com.example.apprender.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepo {

    private val db = FirebaseFirestore.getInstance()
    private val data = MutableLiveData<Boolean>()
    private val loginData = MutableLiveData<MutableList<String>>()
    private val estadoLeccion = MutableLiveData<MutableList<String>>()

    fun setUserDataBase(nombre: String, apellido: String, edad: Int, rut: Int, genero: String, creacion: com.google.firebase.Timestamp){

        val user = hashMapOf(
            "nombre" to nombre,
            "apellido" to apellido,
            "edad" to edad,
            "rut" to rut,
            "genero" to genero,
            "creacion" to creacion
        )

        db.collection("usuarios").document(rut.toString()).set(user).addOnCompleteListener {

            isDataPushed(it.isSuccessful)
        }
    }

    fun setLeccionDataBase(capitulo: String, numLeccion: String, puntaje: Int, tiempo: Int, correctas: Int, incorrectas: Int, estado: String, rut: String){

        val leccionData = hashMapOf(
            "puntaje" to puntaje,
            "tiempo" to tiempo,
            "correctas" to correctas,
            "incorrectas" to incorrectas,
            "estado" to estado
        )

        db.collection("usuarios").document(rut).collection(capitulo).document(numLeccion).set(leccionData).addOnCompleteListener {
            isDataPushed(it.isSuccessful)
        }
    }

    fun setEncuestaDataBase(respuesta1: String, respuesta2: String, respuesta3: String, rut: String){

        val encuesta = hashMapOf(
            "respuesta_1" to respuesta1,
            "respuesta_2" to respuesta2,
            "respuesta_3" to respuesta3
        )

        db.collection("usuarios").document(rut).collection("encuesta").document("encuesta - $rut").set(encuesta).addOnCompleteListener {
            isDataPushed(it.isSuccessful)
        }
    }

    fun updateEstadoLeccion(rut: String, capitulo: String, leccion: String, estado: String){
        db.collection("usuarios").document(rut).collection(capitulo).document(leccion).update("estado",estado).addOnSuccessListener {
            Log.d("update","Estado de la lección actualizado a $it")
        }.addOnFailureListener {
            Log.w("error","no se pudo actualizar el estado", it)
        }
    }

    fun loginUser (rut: String){
        val loginList = mutableListOf<String>()
        db.collection("usuarios").document(rut).get().addOnSuccessListener {
            if (it.exists()){
                val rutDb = it.data?.get("rut").toString()
                val nombre = it.getString("nombre")

                if(rut == rutDb){
                    isDataPushed(true)

                    loginList.add(rutDb)
                    loginList.add(nombre!!)

                    setLoginData(loginList)
                }else{
                    isDataPushed(false)
                }
            }else{
                isDataPushed(false)
            }
        }
    }

    fun getLeccion(rut: String, capitulo: String, posicion: Int){
        val list = mutableListOf<String>()
        db.collection("usuarios").document(rut).collection(capitulo).get().addOnSuccessListener { result ->
            for (leccion in result){
                when (posicion){
                    0 -> {
                        if (leccion.id == "leccion_1"){
                            list.add(leccion.id)
                            list.add(leccion["estado"].toString())
                        }
                    }
                    1 -> {
                        if (leccion.id == "leccion_2"){
                            list.add(leccion.id)
                            list.add(leccion["estado"].toString())
                        }
                    }
                    2 -> {
                        if (leccion.id == "leccion_3"){
                            list.add(leccion.id)
                            list.add(leccion["estado"].toString())
                        }
                    }
                    3 -> {
                        if (leccion.id == "leccion_4"){
                            list.add(leccion.id)
                            list.add(leccion["estado"].toString())
                        }
                    }
                }
            }
            setEstadoLeccion(list)
        }
    }

    fun getStateLeccionForChapter(rut: String, capitulo: String, leccion: String, posicion: Int){
        val list = mutableListOf<String>()
        db.collection("usuarios").document(rut).collection(capitulo).document(leccion).get().addOnSuccessListener {
            if (it.exists()){
                list.add(posicion.toString())
                list.add(it.getString("estado")!!)
            }
            setEstadoLeccion(list)
        }
    }

    private fun isDataPushed(value: Boolean){
        data.value = value
    }

    fun getDataResult(): LiveData<Boolean>{
        return data
    }

    private fun setLoginData(mutableList: MutableList<String>){
        loginData.value = mutableList
    }

    fun getLoginData(): LiveData<MutableList<String>>{
        return loginData
    }

    private fun setEstadoLeccion(mutableList: MutableList<String>){
        estadoLeccion.value = mutableList
    }

    fun getEstadoLeccion(): LiveData<MutableList<String>>{
        return estadoLeccion
    }
}