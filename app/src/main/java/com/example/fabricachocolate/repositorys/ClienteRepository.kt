package com.example.fabricachocolate.repositorys

import com.example.fabricachocolate.models.Cliente
import com.example.fabricachocolate.models.ClienteDAO

class ClienteRepository(private val dao: ClienteDAO) {
    suspend fun inserir(cliente: Cliente) {
        dao.inserir(cliente)
    }
    suspend fun buscar(cpf: String): Cliente? {
        return dao.buscarPorCpf(cpf)
    }
    suspend fun buscarTodas(): List<Cliente> {
        return dao.buscarTodosClientes()
    }
    suspend fun atualizar(cliente: Cliente) {
        dao.atualizar(cliente)
    }
    suspend fun deletar(cliente: Cliente) {
        dao.deletar(cliente)
    }
}