package com.example.lab3
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Toast.makeText(this, "On Create is Called!", Toast.LENGTH_LONG).show()
            Main()
        }
    }

    override fun onPause() {
        Toast.makeText(this, "On Paused Called", Toast.LENGTH_LONG).show()
        super.onPause()
    }

    override fun onRestart() {
        Toast.makeText(this, "On Restart Called", Toast.LENGTH_LONG).show()
        super.onRestart()
    }

    override fun onResume() {
        Toast.makeText(this, "On Resume Called", Toast.LENGTH_LONG).show()
        super.onResume()
    }

    override fun onDestroy() {
        Toast.makeText(this, "On Destory Called", Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}


@Composable fun HomeScreen(nav:NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(20.dp).fillMaxSize()
    ) {
        Text(text = "For Task One.. Press Home Button, Rotate or Kill Application")
        Button(onClick = {
            nav.navigate("/signup")
        }) {
            Text(text = "Task 2 SignUp/Login")
        }
        Button(onClick = { nav.navigate("/application") }) {
            Text(text = "Task 3 Application")
        }
    }
}

@Composable
fun Main(context: Context = LocalContext.current) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/"){
        composable("/"){
            HomeScreen(navController)
        }
        composable("/signup"){
            SignUpScreen(navController)
        }
        composable("/login"){
            LoginScreen(navController)
        }
        composable("/application"){
            friendsr(navController)
        }
        composable("/details_friends/{id}",){ it ->
            var argument = it.arguments?.getString("id")
            if (argument != null) {
                friendsr_detail(id= argument.toInt(), nav=navController)
            }else{
                Toast.makeText(context, "Id not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Main()
}