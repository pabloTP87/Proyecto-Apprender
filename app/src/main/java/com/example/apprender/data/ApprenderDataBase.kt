package com.example.apprender.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database (entities = [Usuario::class], version = 1)
abstract class ApprenderDataBase : RoomDatabase (){

    abstract fun usuarioDao() : UsuarioDao
}