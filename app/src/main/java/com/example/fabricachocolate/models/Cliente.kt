package com.example.fabricachocolate.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "cliente")
data class Cliente(
    @PrimaryKey
    val cpf: String,
    val nome: String,
    val idade: Int,
    val telefone: String,
    val email: String,
)
