package com.pra.roomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pra.roomdatabase.model.Products

@Database(entities = [Products::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun roomDao(): RoomDaO

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