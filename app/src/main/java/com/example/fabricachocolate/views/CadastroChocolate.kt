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
import com.example.fabricachocolate.controllers.ChocolateController
import com.example.fabricachocolate.models.Chocolate


@Composable
fun CadastroChocolate(navController: NavController, chocolateController: ChocolateController) {
    var codigo by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var cor by remember { mutableStateOf("") }
    var qtd_cacau by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    val lista = remember { mutableStateListOf<Chocolate>() }

    fun atualizarLista() {
        chocolateController.buscarTodos { chocolates ->
            lista.clear()
            lista.addAll(chocolates)
        }
    }

    fun limparCampos(){
        codigo = ""
        cpf = ""
        cor = ""
        qtd_cacau = ""
        preco = ""
    }

    LaunchedEffect(Unit) {
        atualizarLista()
    }

    Column(modifier = Modifier.padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Chocolates")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it },
            label = { Text("Codigo") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF Cliente") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cor,
            onValueChange = { cor = it },
            label = { Text("Cor") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = qtd_cacau,
            onValueChange = { qtd_cacau = it },
            label = { Text("Quantidade Cacau (20 a 90)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(onClick = {
                chocolateController.cadastrar(Chocolate(codigo, cpf, cor, qtd_cacau.toIntOrNull() ?: 0, preco.toDoubleOrNull() ?: 0.0)) { sucesso ->
                    if (sucesso) {
                        Log.i("Insert", "Cadastro realizado!")
                        atualizarLista()
                    } else {
                        Log.i("Insert", "Falha ao cadastrar chocolate")
                    }
                }
                limparCampos()
            }, shape = RoundedCornerShape(20.dp)) {
                Text(text = "Inserir")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                chocolateController.atualizar(Chocolate(codigo, cpf, cor, qtd_cacau.toIntOrNull() ?: 0, preco.toDoubleOrNull() ?: 0.0)) { sucesso ->
                    if (sucesso) {
                        Log.i("Update", "Atualização realizada!")
                        atualizarLista()
                    } else {
                        Log.i("Update", "Falha ao atualizar chocolate")
                    }
                }
                limparCampos()
            }, shape = RoundedCornerShape(20.dp)) {
                Text(text = "Atualizar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                chocolateController.deletar(Chocolate(codigo, cpf, cor, qtd_cacau.toIntOrNull() ?: 0, preco.toDoubleOrNull() ?: 0.0)) { sucesso ->
                    if (sucesso) {
                        Log.i("Delete", "Exclusão realizada!")
                        atualizarLista()
                    } else {
                        Log.i("Delete", "Falha ao excluir chocolate")
                    }
                }
                limparCampos()
            }, shape = RoundedCornerShape(20.dp)) {
                Text(text = "Excluir")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(lista) { chocolate ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            codigo = chocolate.codigo
                            cpf = chocolate.cpf_cliente
                            cor = chocolate.cor
                            qtd_cacau = chocolate.qtd_cacau.toString()
                            preco = chocolate.preco.toString()
                        }
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Codigo: ${chocolate.codigo}")
                        Text("CPF Cliente: ${chocolate.cpf_cliente}")
                        Text("Cor: ${chocolate.cor}")
                        Text("Quantidade Cacau (20 a 90): ${chocolate.qtd_cacau}")
                        Text("Preço: ${chocolate.preco}")
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