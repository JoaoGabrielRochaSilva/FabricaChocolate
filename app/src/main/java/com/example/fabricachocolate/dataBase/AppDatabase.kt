package com.example.fabricachocolate.dataBase
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fabricachocolate.models.Chocolate
import com.example.fabricachocolate.models.ChocolateDAO
import com.example.fabricachocolate.models.Cliente
import com.example.fabricachocolate.models.ClienteDAO

@Database(entities = [Cliente::class, Chocolate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDAO
    abstract fun chocolateDao(): ChocolateDAO
}