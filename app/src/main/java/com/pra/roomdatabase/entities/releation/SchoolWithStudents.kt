package com.pra.roomdatabase.entities.releation

import androidx.room.Embedded
import androidx.room.Relation
import com.pra.roomdatabase.entities.School
import com.pra.roomdatabase.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val student: List<Student>
)
