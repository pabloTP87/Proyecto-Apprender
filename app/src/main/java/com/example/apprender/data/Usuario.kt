package com.example.apprender.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.sql.Date

@Entity (tableName = "usuarios")

data class Usuario(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_usuario") val idUser: Int = 0,
    @ColumnInfo(name = "nombre") @NotNull val nombre: String,
    @ColumnInfo(name = "apaterno") @NotNull val apepat: String,
    @ColumnInfo(name = "edad") @NotNull val edad: Int,
    @ColumnInfo(name = "rut") @NotNull val rut: Int,
    @ColumnInfo(name = "sexo") @NotNull val sexo: Char,
    @ColumnInfo(name = "fecha_creacion") @NotNull val fechaCreacion: String
)