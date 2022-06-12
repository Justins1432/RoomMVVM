package com.example.rommmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rommmvvm.R
import com.example.rommmvvm.model.Author

class AuthorAdapter(
    val context: Context,
    val authorClickDelete: AuthorClickDeleteInterface,
    val authorClick: AuthorClickInterface
) : RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>() {
    private val authorlist = ArrayList<Author>()

    inner class AuthorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val surnameAuthor = itemView.findViewById<TextView>(R.id.txtSurname)
        val nameAuthor = itemView.findViewById<TextView>(R.id.txtName)
        val patronymicAuthor = itemView.findViewById<TextView>(R.id.txtPatronymic)
        val deleteAuthor = itemView.findViewById<ImageView>(R.id.dltEntry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.author_item, parent, false)
        return AuthorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.surnameAuthor.text = authorlist[position].surname
        holder.nameAuthor.text = authorlist[position].name
        holder.patronymicAuthor.text = authorlist[position].patronymic

        holder.deleteAuthor.setOnClickListener{
            authorClickDelete.onDeleteIconClick(authorlist[position])
        }

        holder.itemView.setOnClickListener {
            authorClick.onNoteClick(authorlist[position])
        }
    }

    override fun getItemCount(): Int {
        return authorlist.size
    }

    fun updateList(updList: List<Author>){
        authorlist.clear()
        authorlist.addAll(updList)
        notifyDataSetChanged()
    }

}

interface AuthorClickDeleteInterface {
    fun onDeleteIconClick(author: Author)
}

interface AuthorClickInterface {
    fun onNoteClick(author: Author)
}

