package com.example.apprender.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepo {

    private val db = FirebaseFirestore.getInstance()
    private val data = MutableLiveData<Boolean>()
    private val loginData = MutableLiveData<MutableList<String>>()

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
}