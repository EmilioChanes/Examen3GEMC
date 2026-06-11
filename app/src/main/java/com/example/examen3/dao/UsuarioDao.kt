package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examen3.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun registrarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE usuario = :username LIMIT 1")
    suspend fun obtenerUsuario(username: String): Usuario?
}