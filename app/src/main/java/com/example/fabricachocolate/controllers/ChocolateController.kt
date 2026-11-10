package com.example.fabricachocolate.controllers

import android.util.Log
import com.example.fabricachocolate.models.Chocolate
import com.example.fabricachocolate.repositorys.ChocolateRepository
import com.example.fabricachocolate.repositorys.ClienteRepository
import com.example.fabricachocolate.viewModels.ChocolateViewModel
import com.example.fabricachocolate.viewModels.ClienteViewModel

class ChocolateController(repository: ChocolateRepository, repositoryCliente: ClienteRepository) {
    private val viewModel = ChocolateViewModel(repository)
    private val viewModelCliente = ClienteViewModel(repositoryCliente)

    fun isValido(chocolate: Chocolate) : Boolean {
        return chocolate.codigo.isNotBlank() &&
                chocolate.cpf_cliente.isNotBlank() &&
                chocolate.cor.isNotBlank() &&
                chocolate.qtd_cacau >= 20 &&
                chocolate.qtd_cacau <= 90 &&
                chocolate.preco > 0
    }

    fun cadastrar(chocolate: Chocolate, callback: (Boolean) -> Unit) {

        if (!isValido(chocolate)) {
            Log.i("Insert", "Dados inválidos para inserção")
            callback(false)
            return
        }

        viewModel.buscar(chocolate.codigo) { existente ->

            if (existente != null) {
                Log.i("Insert", "Já existe um chocolate com esse Código")
                callback(false)
            } else {
                viewModelCliente.buscar(chocolate.cpf_cliente) { existente ->
                    if(existente != null) {
                        viewModel.inserir(chocolate) {
                            Log.i("Insert", "Chocolate cadastrado com sucesso")
                            callback(true)
                        }
                    } else {
                        Log.i("Insert", "Não existe um cliente com esse CPF")
                        callback(false)
                    }
                }
            }
        }
    }

    fun buscarTodos(callback: (List<Chocolate>) -> Unit) {
        viewModel.buscarTodos(callback)
    }

    fun atualizar(chocolate: Chocolate, callback: (Boolean) -> Unit) {

        if (!isValido(chocolate)) {
            Log.i("Update", "Dados inválidos para atualização")
            callback(false)
            return
        }

        viewModel.buscar(chocolate.codigo) { existente ->
            if (existente != null) {
                viewModelCliente.buscar(chocolate.cpf_cliente) { existenteCliente ->
                    if (existenteCliente != null) {
                        viewModel.atualizar(chocolate) {
                            Log.i("Update", "Chocolate atualizado com sucesso")
                            callback(true)
                        }
                    } else {
                        Log.i("Update", "Não existe um cliente com esse CPF")
                        callback(false)
                    }

                }
            } else {
                Log.i("Update", "Não existe um chocolate com esse Código")
                callback(false)
            }
        }
    }

    fun deletar(chocolate: Chocolate, callback: (Boolean) -> Unit) {

        if (chocolate.codigo.isBlank()) {
            Log.i("Delete", "Código vázio")
            callback(false)
            return
        }

        viewModel.buscar(chocolate.codigo) { existente ->

            if (existente != null) {
                viewModel.deletar(existente) {
                    Log.i("Delete", "Chocolate deletado com sucesso")
                    callback(true)
                }
            } else {
                Log.i("Delete", "Não existe um chocolate com esse código")
                callback(false)
            }
        }
    }
}