package com.example.fabricachocolate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.fabricachocolate.controllers.ChocolateController
import com.example.fabricachocolate.controllers.ClienteController
import com.example.fabricachocolate.dataBase.AppDatabase
import com.example.fabricachocolate.repositorys.ChocolateRepository
import com.example.fabricachocolate.repositorys.ClienteRepository
import com.example.fabricachocolate.views.CadastroChocolate
import com.example.fabricachocolate.views.CadastroCliente
import com.example.fabricachocolate.views.TelaPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "banco_fabrica"
            ).build()
            val dao = db.clienteDao()
            val repository = ClienteRepository(dao)
            val clienteController = ClienteController(repository)

            val daoChocolate = db.chocolateDao()
            val repositoryChocolate = ChocolateRepository(daoChocolate)
            val chocolateController = ChocolateController(repositoryChocolate, repository)

            Telas(clienteController, chocolateController)
        }
    }
}

@Composable
fun Telas(clienteController: ClienteController, chocolateController: ChocolateController) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "principal"
    ) {
        composable("principal") {
            TelaPrincipal(navController)
        }
        composable("cadastroCliente") {
            CadastroCliente(navController, clienteController)
        }
        composable("cadastroChocolate") {
            CadastroChocolate(navController, chocolateController)
        }
    }
}
