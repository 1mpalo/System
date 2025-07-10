package com.example.system.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.system.R
import com.example.system.presentation.common.Button
import com.example.system.presentation.common.GlowingText
import com.example.system.presentation.common.Notification
import com.example.system.ui.theme.SystemTheme

@Composable
fun WelcomingScreen(
    onContinueClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Notification(
                text = "NOTIFICATION"
            )
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
                contentAlignment = Alignment.Center
            ) {
                GlowingText(
                    text = "Congratulations on becoming a Player",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize
                )
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
            Button(
                text = "Continue",
                height = 50.dp,
                width = 160.dp,
                onClick = onContinueClick
            )
        }
    }
}

@Preview()
@Composable
private fun WelcomingScreenPreview() {
    SystemTheme {
        WelcomingScreen(onContinueClick = {})
    }
}