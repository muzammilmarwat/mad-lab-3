package com.example.jokesprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.jokesprovider.db.Joke
import com.example.jokesprovider.repo.JokeRepo

class JokeProvider : ContentProvider() {

    companion object {
        private const val JOKES = 1
        private const val AUTHORITY = "com.example.jokesprovider.provider"
        private const val JOKES_PATH = "jokes"
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, JOKES_PATH, JOKES)
    }
    private lateinit var repo: JokeRepo

    override fun onCreate(): Boolean {
        context?.let {
            repo = JokeRepo(it)
        }
        return ::repo.isInitialized
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            JOKES -> repo.getJokes()
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            JOKES -> "vnd.android.cursor.dir/vnd.$AUTHORITY.$JOKES_PATH"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        values?.getAsString("joke")?.let {
            repo.insertAll(Joke(0, it))
        }
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        // Deletion is not supported
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        // Update is not supported
        return 0
    }
}
