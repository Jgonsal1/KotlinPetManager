package com.example.kotlinpetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kotlinpetmanager.ui.theme.KotlinPetManagerTheme
import com.example.kotlinpetmanager.view.PacienteScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinPetManagerTheme {
                PacienteScreen()
            }
        }
    }
}