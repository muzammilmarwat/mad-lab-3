package com.example.jokesprovider.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDAO {

    @Query("SELECT * FROM Joke")
    fun getAll(): Cursor

    @Query("SELECT * FROM Joke WHERE id = :id")
    fun getById(id: Int): Cursor

    @Insert
    fun insertAll(vararg jokes: Joke)

    @Delete
    fun delete(joke: Joke)

}