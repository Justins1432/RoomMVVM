package com.example.rommmvvm.view

import androidx.recyclerview.widget.DiffUtil
import com.example.rommmvvm.model.Author

class AuthorsDiffUtilCallback(
    private val oldList: List<Author>,
    private val newList: List<Author>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAuthor: Author = oldList[oldItemPosition]
        val newAuthor: Author = newList[newItemPosition]
        return oldAuthor.id == newAuthor.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAuthor: Author = oldList[oldItemPosition]
        val newAuthor: Author = newList[newItemPosition]
        return oldAuthor.name == newAuthor.name && oldAuthor.surname == newAuthor.surname &&
                oldAuthor.patronymic == newAuthor.patronymic
    }
}