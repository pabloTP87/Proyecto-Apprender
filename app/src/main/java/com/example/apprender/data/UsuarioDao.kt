package com.example.apprender.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuarios")
    fun allUsers() : List<Usuario>

    @Insert
    fun addUser( vararg usuario: Usuario)
}