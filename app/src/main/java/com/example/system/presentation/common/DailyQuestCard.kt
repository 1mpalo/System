package com.example.system.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.system.domain.model.DailyQuest

@Composable
fun DailyQuestCard(
    task: DailyQuest,
    onDoneClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(125.dp)
            .background(Color.Black.copy(alpha = 0.07f)).border(0.4.dp, Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            GlowingText(text = task.quest, fontSize = MaterialTheme.typography.headlineMedium.fontSize, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            GlowingText(text = "By finishing this quest you will be rewarded with ${task.points}", fontSize = MaterialTheme.typography.titleSmall.fontSize, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Button(text = "Done", height = 30.dp, width = 100.dp, onClick = { onDoneClick() })
        }
    }
}