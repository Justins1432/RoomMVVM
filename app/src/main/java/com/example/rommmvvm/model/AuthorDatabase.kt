package com.example.rommmvvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Author::class], version = 1, exportSchema = false)
abstract class AuthorDatabase : RoomDatabase() {
    abstract fun getAuthorDAO(): AuthorDao

    companion object {
        @Volatile
        private var INSTANCE: AuthorDatabase? = null
    }
}
