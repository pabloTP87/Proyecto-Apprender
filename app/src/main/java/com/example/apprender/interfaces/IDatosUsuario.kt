package com.example.apprender.interfaces

interface IDatosUsuario {
    fun nombreUsuario(nombre: String)
    fun lastName(lastName: String)
    fun userAge (edad: Int)
    fun rutUsuario(rut: String, dv: String)
    fun generoUsuario(genero: String)
}