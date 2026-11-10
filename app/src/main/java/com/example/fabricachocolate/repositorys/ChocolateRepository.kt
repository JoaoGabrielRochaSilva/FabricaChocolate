package com.example.fabricachocolate.repositorys

import com.example.fabricachocolate.models.Chocolate
import com.example.fabricachocolate.models.ChocolateDAO

class ChocolateRepository(private val dao: ChocolateDAO) {
    suspend fun inserir(chocolate: Chocolate) {
        dao.inserir(chocolate)
    }

    suspend fun buscar(codigo: String) : Chocolate? {
        return dao.buscarPorCodigo(codigo)
    }

    suspend fun buscarTodos() : List<Chocolate> {
        return dao.buscarTodosChocolates()
    }

    suspend fun atualizar(chocolate: Chocolate) {
        dao.atualizar(chocolate)
    }

    suspend fun deletar(chocolate: Chocolate) {
        dao.deletar(chocolate)
    }
}