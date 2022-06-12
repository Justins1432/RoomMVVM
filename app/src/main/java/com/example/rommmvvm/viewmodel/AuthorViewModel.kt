package com.example.rommmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rommmvvm.model.Author
import com.example.rommmvvm.model.AuthorDatabase
import com.example.rommmvvm.model.AuthorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorViewModel(application: Application) : AndroidViewModel(application) {
    val allAuthors: LiveData<List<Author>>
    val repository: AuthorRepository

    init {
        val dao = AuthorDatabase.getDatabase(application).getAuthorDAO()
        repository = AuthorRepository(dao)
        allAuthors = repository.allAuthors
    }

    fun deleteAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(author)
    }

    fun updateAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(author)
    }

    fun addAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(author)
    }

}