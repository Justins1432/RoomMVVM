package com.example.rommmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rommmvvm.R
import com.example.rommmvvm.model.Author
import com.example.rommmvvm.viewmodel.AuthorViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AuthorClickDeleteInterface, AuthorClickInterface {
    private lateinit var recyclerViewAuthors: RecyclerView
    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var viewModel: AuthorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents(){
        recyclerViewAuthors = findViewById(R.id.recycler_view_authors)
        buttonAdd = findViewById(R.id.btnAdd)
        initAuthors()
        addAuthor()
    }

    private fun initAuthors(){
        recyclerViewAuthors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val authorAdapter = AuthorAdapter(this, this, this)
        recyclerViewAuthors.adapter = authorAdapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[AuthorViewModel::class.java]
        viewModel.allAuthors.observe(this, Observer { list ->
            list?.let {
                authorAdapter.updateList(it)
            }
        })
    }

    private fun addAuthor(){
        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddAuthorActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDeleteIconClick(author: Author) {
        viewModel.deleteAuthor(author)
        Toast.makeText(this, "${author.surname} удалён", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(author: Author) {
        val intent = Intent(this, AddAuthorActivity::class.java)
        intent.putExtra("type", "Edit")
        intent.putExtra("id", author.id)
        intent.putExtra("surname", author.surname)
        intent.putExtra("name", author.name)
        intent.putExtra("patronymic", author.patronymic)
        startActivity(intent)
        finish()
    }

}