package com.pra.roomdatabase.entities.releation

import androidx.room.Embedded
import androidx.room.Relation
import com.pra.roomdatabase.entities.Director
import com.pra.roomdatabase.entities.School

data class SchoolAndDirector (
    @Embedded val school: School,
    @Relation(
       parentColumn = "schoolName",
       entityColumn = "schoolName"
   )
   val director: Director
)