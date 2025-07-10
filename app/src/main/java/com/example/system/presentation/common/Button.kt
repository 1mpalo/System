package com.example.system.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    text: String,
    height: Dp,
    width: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(height)
            .width(width)
            .border(0.4.dp, Color.White)
            .background(Color.Black.copy(0.3f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        GlowingText(
            text = text,
            color = Color.White,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }
}