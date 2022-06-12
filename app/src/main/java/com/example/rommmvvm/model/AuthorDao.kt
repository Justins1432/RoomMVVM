package com.example.rommmvvm.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(author: Author)

    @Update
    suspend fun update(author: Author)

    @Delete
    suspend fun delete(author: Author)

    @Query("SELECT * FROM authors ORDER BY id ASC")
    fun getAuthors(): LiveData<List<Author>>

}