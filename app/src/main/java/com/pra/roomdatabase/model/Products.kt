package com.pra.roomdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Products(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    var title: String
)