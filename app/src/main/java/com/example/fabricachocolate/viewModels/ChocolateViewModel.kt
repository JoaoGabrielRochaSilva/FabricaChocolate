package com.example.fabricachocolate.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabricachocolate.models.Chocolate
import com.example.fabricachocolate.repositorys.ChocolateRepository
import kotlinx.coroutines.launch

class ChocolateViewModel(private val repository: ChocolateRepository) : ViewModel() {

    fun inserir(chocolate: Chocolate, callback: () -> Unit) {
        viewModelScope.launch {
            repository.inserir(chocolate)
            callback()
        }
    }

    fun buscar(codigo: String, callback: (Chocolate?) -> Unit) {
        viewModelScope.launch {
            val chocolate = repository.buscar(codigo)
            callback(chocolate)
        }
    }

    fun buscarTodos(callback: (List<Chocolate>) -> Unit) {
        viewModelScope.launch {
            val lista = repository.buscarTodos()
            callback(lista)
        }
    }

    fun atualizar(chocolate: Chocolate, callback: () -> Unit) {
        viewModelScope.launch {
            repository.atualizar(chocolate)
            callback()
        }
    }

    fun deletar(chocolate: Chocolate, callback: () -> Unit) {
        viewModelScope.launch {
            repository.deletar(chocolate)
            callback()
        }
    }


}