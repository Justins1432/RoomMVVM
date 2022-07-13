package com.example.rommmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rommmvvm.model.Author
import com.example.rommmvvm.model.AuthorApplication
import com.example.rommmvvm.model.AuthorDatabase
import com.example.rommmvvm.model.AuthorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorViewModel(application: Application) : AndroidViewModel(application) {
    var allAuthors: LiveData<List<Author>>
        private set
    val repository: AuthorRepository

    init {
        val dao = AuthorApplication.database
        repository = AuthorRepository(dao.getAuthorDAO())
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