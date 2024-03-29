package com.example.fragmentlogandsign.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class],
    version = 1
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun getDao(): DAO

    companion object{
        fun getDB(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "main.db"
            ).build()
        }
    }
}