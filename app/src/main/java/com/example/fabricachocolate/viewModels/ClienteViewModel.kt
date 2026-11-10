package com.example.fabricachocolate.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabricachocolate.models.Cliente
import com.example.fabricachocolate.repositorys.ClienteRepository
import kotlinx.coroutines.launch

class ClienteViewModel(private val repository: ClienteRepository) : ViewModel() {

    fun inserir(cliente: Cliente, callback: () -> Unit) {
        viewModelScope.launch {
            repository.inserir(cliente)
            callback()
        }
    }

    fun buscar(cpf: String, callback: (Cliente?) -> Unit) {
        viewModelScope.launch {
            val pessoa = repository.buscar(cpf)
            callback(pessoa)
        }
    }

    fun buscarTodas(callback: (List<Cliente>) -> Unit) {
        viewModelScope.launch {
            val lista = repository.buscarTodas()
            callback(lista)
        }
    }

    fun atualizar(cliente: Cliente, callback: () -> Unit) {
        viewModelScope.launch {
            repository.atualizar(cliente)
            callback()
        }
    }

    fun deletar(cliente: Cliente, callback: () -> Unit) {
        viewModelScope.launch {
            repository.deletar(cliente)
            callback()
        }
    }
}