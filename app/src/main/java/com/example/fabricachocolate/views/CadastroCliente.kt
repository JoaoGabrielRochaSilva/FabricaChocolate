package com.example.fabricachocolate.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fabricachocolate.controllers.ClienteController
import com.example.fabricachocolate.models.Cliente

@Composable
fun CadastroCliente(navController: NavController, clienteController: ClienteController){

    var cpf by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    val lista = remember { mutableStateListOf<Cliente>() }
    fun atualizarLista() {
        clienteController.buscarTodos { clientes ->
            lista.clear()
            lista.addAll(clientes)
        }
    }
    fun limparCampos(){
        cpf = ""
        nome = ""
        idade = ""
        email = ""
        telefone = ""
    }

    LaunchedEffect(Unit) {
        atualizarLista()
    }

    Column(modifier = Modifier.padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Clientes")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = idade,
            onValueChange = { idade = it },
            label = { Text("Idade") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(onClick = {
                clienteController.cadastrar(Cliente(cpf, nome, idade.toIntOrNull() ?: 0, telefone, email)) { sucesso ->
                    if (sucesso) {
                        Log.i("Insert", "Cadastro realizado!")
                        atualizarLista()
                    } else {
                        Log.i("Insert", "Falha ao cadastrar cliente")
                    }
                }
                limparCampos()
            }) {
                Text("Inserir")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                clienteController.atualizar(Cliente(cpf, nome, idade.toIntOrNull() ?: 0, telefone, email)) { sucesso ->
                    if (sucesso) {
                        Log.i("Update", "Atualização realizada!")
                        atualizarLista()
                    } else {
                        Log.i("Update", "Falha ao atualizar cliente")
                    }
                }
                limparCampos()
            }) {
                Text("Atualizar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                clienteController.deletar(Cliente(cpf, nome, idade.toIntOrNull() ?: 0, telefone, email)) { sucesso ->
                    if (sucesso) {
                        Log.i("Delete", "Exclusão realizada!")
                        atualizarLista()
                    } else {
                        Log.i("Delete", "Falha ao excluir cliente")
                    }
                }
                limparCampos()
            }) {
                Text("Excluir")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(lista) { cliente ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            cpf = cliente.cpf
                            nome = cliente.nome
                            idade = cliente.idade.toString()
                            telefone = cliente.telefone
                            email = cliente.email
                        }
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("CPF: ${cliente.cpf}")
                        Text("Nome: ${cliente.nome}")
                        Text("Idade: ${cliente.idade}")
                        Text("Telefone: ${cliente.telefone}")
                        Text("Email: ${cliente.email}")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.popBackStack()
        }, shape = RoundedCornerShape(20.dp)) {
            Text(text = "Voltar")
        }
    }
}
