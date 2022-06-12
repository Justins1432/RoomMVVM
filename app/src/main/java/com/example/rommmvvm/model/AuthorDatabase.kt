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

        fun getDatabase(context: Context): AuthorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AuthorDatabase::class.java,
                    "author_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}