package com.example.jokesprovider

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokesprovider.ui.theme.JokesProviderAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        CoroutineScope(Dispatchers.IO).launch {
            printJokes()
        }

        setContent {
            JokesProviderAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }

    private suspend fun printJokes() {
        val providerUri = Uri.parse("content://com.example.jokesprovider.provider/jokes")
        contentResolver.query(providerUri, null, null, null, null)?.use { cursor ->
            while (cursor.moveToNext()) {
                val joke = cursor.getString(1)
                Log.d("JOKES_PRINTER", "Joke: $joke")
            }
        }
    }
}

@Composable
internal fun App() {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("Jokes List") },
                modifier = Modifier.padding(16.dp)
            )
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Text("Display jokes here", modifier = Modifier.padding(16.dp))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    JokesProviderAppTheme {
        App()
    }
}
