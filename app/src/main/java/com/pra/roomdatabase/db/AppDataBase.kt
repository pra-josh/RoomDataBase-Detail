package com.pra.roomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pra.roomdatabase.entities.Director
import com.pra.roomdatabase.entities.School
import com.pra.roomdatabase.entities.Student

@Database(entities = [Student::class, Director::class, School::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

    companion object {

        @Volatile
        private var Instance: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, AppDataBase::class.java, "AppDb.db"
                ).allowMainThreadQueries().build().also {
                    Instance = it
                }
            }
        }
    }
}