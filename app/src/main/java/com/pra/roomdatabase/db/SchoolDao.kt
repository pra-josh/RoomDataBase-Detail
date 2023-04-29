package com.pra.roomdatabase.db

import androidx.room.*
import com.pra.roomdatabase.entities.Director
import com.pra.roomdatabase.entities.School
import com.pra.roomdatabase.entities.Student
import com.pra.roomdatabase.entities.releation.SchoolAndDirector
import com.pra.roomdatabase.entities.releation.SchoolWithStudents

@Dao
interface SchoolDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Transaction
    @Query("SELECT * FROM School WHERE schoolName = :schoolName")
    suspend fun  getSchoolAndDirectorWithSchoolName(schoolName:String):List<SchoolAndDirector>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM School WHERE schoolName = :schoolName")
    suspend fun  getSchoolWithStudents(schoolName:String):List<SchoolWithStudents>

}