package com.example.rommmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rommmvvm.R
import com.example.rommmvvm.model.Author
import com.example.rommmvvm.viewmodel.AuthorViewModel

class AddAuthorActivity : AppCompatActivity() {
    private lateinit var inpSurname: EditText
    private lateinit var inpName: EditText
    private lateinit var inpPatronymic: EditText
    private lateinit var btnAdd: Button
    private lateinit var viewModel: AuthorViewModel
    private var idAuthor: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_author)
        initComponents()
    }

    private fun initComponents() {
        inpSurname = findViewById(R.id.addSurname)
        inpName = findViewById(R.id.addName)
        inpPatronymic = findViewById(R.id.addPatronymic)
        btnAdd = findViewById(R.id.btnAddAuthor)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AuthorViewModel::class.java]

        initUpdateAndAddAuthor()
    }

    private fun initUpdateAndAddAuthor() {
        val type = intent.getStringExtra("type")
        if (type.equals("Edit")) {
            idAuthor = intent.getLongExtra("id", -1)
            val surname = intent.getStringExtra("surname")
            val name = intent.getStringExtra("name")
            val patronymic = intent.getStringExtra("patronymic")
            btnAdd.text = "Обновить"
            inpSurname.setText(surname)
            inpName.setText(name)
            inpPatronymic.setText(patronymic)
        } else {
            btnAdd.text = "Добавить"
        }

        btnAdd.setOnClickListener {
            val surnameAuthor = inpSurname.text.toString()
            val nameAuthor = inpName.text.toString()
            val patronymicAuthor = inpPatronymic.text.toString()

            if (type.equals("Edit")) {
                if (surnameAuthor.isNotEmpty() && nameAuthor.isNotEmpty() && patronymicAuthor.isNotEmpty()) {
                    val updateAuthor = Author(surnameAuthor, nameAuthor, patronymicAuthor)
                    updateAuthor.id = idAuthor
                    viewModel.updateAuthor(updateAuthor)
                    Toast.makeText(this, "Author updated....", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (surnameAuthor.isNotEmpty() && nameAuthor.isNotEmpty() && patronymicAuthor.isNotEmpty()) {
                    val insertAuthor = Author(surnameAuthor, nameAuthor, patronymicAuthor)
                    insertAuthor.id = idAuthor
                    viewModel.addAuthor(insertAuthor)
                    Toast.makeText(this, "Author added...", Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

}