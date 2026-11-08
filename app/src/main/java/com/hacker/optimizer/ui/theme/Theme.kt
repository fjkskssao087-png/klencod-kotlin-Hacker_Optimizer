package com.hacker.optimizer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val HackerColors = darkColorScheme(
    primary = Color(0xFFFF003C), // أحمر نيون
    background = Color(0xFF000000), // أسود
    surface = Color(0xFF0A0A0A),
    onPrimary = Color.White,
    onBackground = Color(0xFFFF003C)
)

@Composable
fun HackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = HackerColors, content = content)
}