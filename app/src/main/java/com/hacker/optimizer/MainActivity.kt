package com.hacker.optimizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hacker.optimizer.ui.HackerScreen
import com.hacker.optimizer.ui.theme.HackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HackerTheme {
                HackerScreen()
            }
        }
    }
}