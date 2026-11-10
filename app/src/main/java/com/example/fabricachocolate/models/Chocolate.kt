package com.example.fabricachocolate.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "chocolate",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["cpf"],
            childColumns = ["cpf_cliente"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["cpf_cliente"])
    ]
)
data class Chocolate(
    @PrimaryKey
    val codigo: String,
    val cpf_cliente: String,
    val cor: String,
    val qtd_cacau: Int,
    val preco: Double
)
