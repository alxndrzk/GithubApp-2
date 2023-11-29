package com.example.githubapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Loved::class], version = 1)
abstract class LovedRoomDatabase: RoomDatabase() {

    abstract fun lovedDao() : LovedDao

    companion object {
        @Volatile
        private var INSTANCE: LovedRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) :     LovedRoomDatabase {
            if (INSTANCE == null) {
                synchronized(LovedRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        LovedRoomDatabase::class.java, "loved_database")
                        .build()
                }
            }

            return INSTANCE as LovedRoomDatabase

        }
    }
}