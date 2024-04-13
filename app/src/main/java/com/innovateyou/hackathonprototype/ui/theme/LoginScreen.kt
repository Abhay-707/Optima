package com.innovateyou.hackathonprototype.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import com.innovateyou.hackathonprototype.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.innovateyou.hackathonprototype.data.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var loginResult by remember { mutableStateOf<Result<String>>(Result.Success("")) }
    val checkboxColors = CheckboxDefaults.colors(
        checkedColor = ThemeBlueStart, // Color when the checkbox is checked
        uncheckedColor = Color.Gray, // Color when the checkbox is unchecked
        disabledColor = Color.LightGray, // Color when the checkbox is disabled
        checkmarkColor = Color.White // Color of the checkmark inside the checkbox
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = ThemeBlueStart,
                focusedLabelColor = ThemeBlueStart
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = ThemeBlueStart,
                focusedLabelColor = ThemeBlueStart
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                colors = checkboxColors,
                checked = showPassword,
                onCheckedChange = { showPassword = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Show Password")
        }
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = loginUser(email, password)
                    withContext(Dispatchers.Main) {
                        loginResult = result
                        if (result is Result.Success) {
                            Toast.makeText(context, "Login Successful! ", Toast.LENGTH_LONG).show()
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo("login_route") { inclusive = true }
                            }
                        } else if (result is Result.Error) {
                            Toast.makeText(context, "Login failed: ${result.exception.message}", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(50))
        ) {
            Text("Login")
        }
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {Image(
        painter = painterResource(id = R.drawable.optima),
        contentDescription = "Health Icon",
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
        )


        Text(
            text = "Log In",


            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                fontFamily = harmoniaSansFamily,
                textAlign = TextAlign.Center
            )
        )
    }
}

