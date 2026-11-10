package com.example.fabricachocolate.models

import androidx.room.*
@Dao
interface ClienteDAO {
    @Insert
    suspend fun inserir(cliente: Cliente)
    @Query("SELECT * FROM cliente WHERE cpf = :cpf")
    suspend fun buscarPorCpf(cpf: String): Cliente?
    @Query("SELECT * FROM cliente")
    suspend fun buscarTodosClientes(): List<Cliente>
    @Update
    suspend fun atualizar(cliente: Cliente)
    @Delete
    suspend fun deletar(cliente: Cliente)

}