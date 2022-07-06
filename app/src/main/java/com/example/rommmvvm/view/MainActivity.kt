package com.example.rommmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rommmvvm.R
import com.example.rommmvvm.model.Author
import com.example.rommmvvm.util.Constants
import com.example.rommmvvm.viewmodel.AuthorViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AuthorClickDeleteInterface, AuthorClickInterface {
    private lateinit var recyclerViewAuthors: RecyclerView
    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var viewModel: AuthorViewModel

    private val authorAdapter = AuthorAdapter(this, this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecyclerView()
        subscribe()
        initListener()
    }

    private fun initView() {
        recyclerViewAuthors = findViewById(R.id.recycler_view_authors)
        buttonAdd = findViewById(R.id.btnAdd)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AuthorViewModel::class.java]
    }

    private fun initRecyclerView() {
        recyclerViewAuthors.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewAuthors.adapter = authorAdapter
    }

    private fun subscribe() {
        viewModel.allAuthors.observe(this, Observer { list ->
            list?.let {
                authorAdapter.updateList(it)
            }
        })
    }

    private fun initListener() {
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
        intent.putExtra(Constants.TYPE, "Edit")
        intent.putExtra(Constants.ID, author.id)
        intent.putExtra(Constants.SURNAME, author.surname)
        intent.putExtra(Constants.NAME, author.name)
        intent.putExtra(Constants.PATRONYMIC, author.patronymic)
        startActivity(intent)
        finish()
    }

}