package com.example.lab3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(nav: NavController) {
    val primaryColor = Color(0xFF2D3236)  // Hexadecimal color value for readability
    val buttonColor = Color(0xFFEA4C87)  // Hexadecimal color value for readability
    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        containerColor = Color(0xFF3C404B),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        cursorColor = Color.White,
        focusedLabelColor = Color.Gray,
        unfocusedLabelColor = Color.Gray
    )

    val emailValue = remember { mutableStateOf("") }
    val nameValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Name TextField
            TextField(
                value = nameValue.value,
                onValueChange = { nameValue.value = it },
                label = { Text(text = "Full Name") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(0.85f)
            )

            // Email TextField
            TextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                label = { Text(text = "Email") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(0.85f)
            )

            // Password Field
            TextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                label = { Text(text = "Password") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(0.85f),
                visualTransformation = PasswordVisualTransformation() // To obscure password input
            )

            // Register Button
            Button(
                onClick = { /* Handle registration */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp) // Rounded corners for the button
            ) {
                Text(text = "Register", color = Color.White)
            }

            // Login Text
            Text(
                text = buildAnnotatedString {
                    append("Already Registered? ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Login Here")
                    }
                },
                color = Color.White,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        nav.navigate("/login")
                    }
            )
        }
    }
}
