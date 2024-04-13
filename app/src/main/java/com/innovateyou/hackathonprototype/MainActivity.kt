package com.innovateyou.hackathonprototype


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.innovateyou.hackathonprototype.ui.theme.HackathonPrototypeTheme
import com.innovateyou.hackathonprototype.ui.theme.MainViewModel
import com.innovateyou.hackathonprototype.ui.theme.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackathonPrototypeTheme(darkTheme = false) {

                SetBarColor(color = Color.White)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Navigation(viewModel = MainViewModel())

                }
            }
        }
    }
}
@Composable
private fun SetBarColor(color: Color){
    val systemUicontroller = rememberSystemUiController()
    SideEffect {
        systemUicontroller.setSystemBarsColor(
            color = color
        )
    }
}

