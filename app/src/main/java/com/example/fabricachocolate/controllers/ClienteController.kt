package com.example.fabricachocolate.controllers

import android.util.Log
import com.example.fabricachocolate.models.Cliente
import com.example.fabricachocolate.repositorys.ClienteRepository
import com.example.fabricachocolate.viewModels.ClienteViewModel

class ClienteController(clienteRepository: ClienteRepository) {
    private val viewModel: ClienteViewModel = ClienteViewModel(clienteRepository)
    fun isValido(cliente: Cliente): Boolean {
        return cliente.cpf.isNotBlank() &&
                cliente.nome.isNotBlank() &&
                cliente.idade > 0 &&
                cliente.telefone.isNotBlank() &&
                cliente.email.isNotBlank()
    }

    // CADASTRAR
    fun cadastrar(cliente: Cliente, callback: (Boolean) -> Unit) {

        if (!isValido(cliente)) {
            Log.i("Insert", "Dados inválidos para inserção")
            callback(false)
            return
        }

        viewModel.buscar(cliente.cpf) { existente ->

            if (existente != null) {
                Log.i("Insert", "Já existe um cliente com esse CPF")
                callback(false)
            } else {
                viewModel.inserir(cliente) {
                    Log.i("Insert", "Cliente cadastrado com sucesso")
                    callback(true)
                }
            }
        }
    }

    // BUSCAR TODOS
    fun buscarTodos(callback: (List<Cliente>) -> Unit) {
        viewModel.buscarTodas(callback)
    }

    // ATUALIZAR
    fun atualizar(cliente: Cliente, callback: (Boolean) -> Unit) {

        if (!isValido(cliente)) {
            Log.i("Update", "Dados inválidos para atualização")
            callback(false)
            return
        }

        viewModel.buscar(cliente.cpf) { existente ->

            if (existente != null) {
                viewModel.atualizar(cliente) {
                    Log.i("Update", "Cliente atualizado com sucesso")
                    callback(true)
                }
            } else {
                Log.i("Update", "Não existe um cliente com esse CPF")
                callback(false)
            }
        }
    }

    // DELETAR
    fun deletar(cliente: Cliente, callback: (Boolean) -> Unit) {

        if (cliente.cpf.isBlank()) {
            Log.i("Delete", "CPF vazio")
            callback(false)
            return
        }

        viewModel.buscar(cliente.cpf) { existente ->

            if (existente != null) {
                viewModel.deletar(existente) {
                    Log.i("Delete", "Cliente deletado com sucesso")
                    callback(true)
                }
            } else {
                Log.i("Delete", "Não existe um cliente com esse CPF")
                callback(false)
            }
        }
    }
}