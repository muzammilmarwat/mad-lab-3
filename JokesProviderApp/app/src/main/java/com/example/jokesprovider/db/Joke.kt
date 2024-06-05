package com.example.jokesprovider.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val joke: String
)
