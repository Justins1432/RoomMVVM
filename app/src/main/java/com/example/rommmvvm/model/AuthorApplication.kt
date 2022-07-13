package com.example.rommmvvm.model

import android.app.Application
import androidx.room.Room

class AuthorApplication : Application() {
    companion object{
        lateinit var database: AuthorDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AuthorDatabase::class.java, "authors_db").build()
    }
}