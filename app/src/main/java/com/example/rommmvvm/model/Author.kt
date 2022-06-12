package com.example.rommmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(
    @ColumnInfo(name = "surname")
    val surname: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "patronymic")
    val patronymic: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
