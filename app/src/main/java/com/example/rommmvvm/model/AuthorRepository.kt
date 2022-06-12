package com.example.rommmvvm.model

import androidx.lifecycle.LiveData

class AuthorRepository(private val authorDAO: AuthorDao) {
    val allAuthors: LiveData<List<Author>> = authorDAO.getAuthors()

    suspend fun insert(author: Author){
        authorDAO.insert(author)
    }

    suspend fun update(author: Author){
        authorDAO.update(author)
    }

    suspend fun delete(author: Author){
        authorDAO.delete(author)
    }

}