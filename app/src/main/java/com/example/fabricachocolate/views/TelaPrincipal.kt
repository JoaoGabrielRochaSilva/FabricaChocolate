package com.example.fabricachocolate.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TelaPrincipal(navController: NavController) {
    Column (modifier = Modifier.padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        Text("Bem-Vindo(a) a Fábrica de Chocolates", textAlign = TextAlign.Center, modifier =
            Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            navController.navigate("cadastroCliente")
        }, shape = RoundedCornerShape(20.dp)) {
            Text(text = "Clientes")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("cadastroChocolate")
        }, shape = RoundedCornerShape(20.dp)) {
            Text(text = "Chocolates")
        }

    }

}