package com.pra.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Subject(
    @PrimaryKey(autoGenerate = false) val subjectName: String,

)