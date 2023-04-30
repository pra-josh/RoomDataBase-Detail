package com.pra.roomdatabase.app

import android.app.Application
import android.content.Context
import com.pra.roomdatabase.db.AppDataBase

class MyApplication : Application() {

    public var appDataBase: AppDataBase? = null

    companion object {
        private var instance: MyApplication? = null;

        fun getAppInstance(): MyApplication? {
            if (instance == null) {
                instance = MyApplication()
            }
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        // appDataBase = AppDataBase.getDataBase(applicationContext)
    }

    fun getRoomDataBaseInstance(context: Context): AppDataBase {
        if (appDataBase == null) {
            appDataBase = AppDataBase.getDataBase(context)
            return appDataBase!!
        } else {
            return appDataBase!!
        }
    }


}