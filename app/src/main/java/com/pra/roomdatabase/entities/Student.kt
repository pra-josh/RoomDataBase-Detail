package com.pra.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student (
    @PrimaryKey(autoGenerate = false)
    val studentName:String,
    val semester :Int,
    val schoolName: String
)