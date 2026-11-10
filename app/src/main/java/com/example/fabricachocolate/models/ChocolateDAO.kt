package com.example.fabricachocolate.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChocolateDAO {
    @Insert
    suspend fun inserir(chocolate: Chocolate)
    @Query("SELECT * FROM chocolate WHERE codigo = :codigo")
    suspend fun buscarPorCodigo(codigo: String): Chocolate?
    @Query("SELECT * FROM chocolate")
    suspend fun buscarTodosChocolates(): List<Chocolate>
    @Update
    suspend fun atualizar(chocolate: Chocolate)
    @Delete
    suspend fun deletar(chocolate: Chocolate)
}