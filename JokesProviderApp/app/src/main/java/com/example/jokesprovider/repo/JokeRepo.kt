package com.example.jokesprovider.repo

import android.content.Context
import androidx.room.Room
import com.example.jokesprovider.db.Joke
import com.example.jokesprovider.db.JokeDatabase

class JokeRepo(context: Context) {
    private val db = Room.databaseBuilder(context, JokeDatabase::class.java, "jokes").build()
    private val dao = db.jokeDao()


    fun getJokes() = dao.getAll()
    fun insertAll(vararg jokes: Joke) = dao.insertAll(*jokes)
    fun delete(joke: Joke) = dao.delete(joke)
    fun jokeById(id: Int) = dao.getById(id)

}