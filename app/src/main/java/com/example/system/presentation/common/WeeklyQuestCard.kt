package com.example.system.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.WeeklyQuest
import com.example.system.ui.theme.SystemTheme

@Composable
fun WeeklyQuestCard(
    task: WeeklyQuest,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    progress: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.6f)

            .background(Color.Black.copy(alpha = 0.07f))
            .border(0.4.dp, Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            GlowingText(text = task.quest, fontSize = MaterialTheme.typography.headlineMedium.fontSize, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Box(modifier = Modifier.fillMaxWidth(0.7f), contentAlignment = Alignment.Center)
            {
                LinearProgressIndicator(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(),
                    progress = { progress },
                    gapSize = (-30).dp
                )

                GlowingText(
                    modifier = Modifier
                        .padding(if (progress.toFloat() == 1f) 5.dp else 17.dp)
                        .fillMaxWidth(),
                    text = "${task.currentTimesDone}/${task.targetTimesDone}",
                    textAlign = TextAlign.End,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(text = "+", height = 30.dp, width = 60.dp, onClick = {onPlusClick()})
                Spacer(modifier = Modifier.width(30.dp))
                Button(text = "-", height = 30.dp, width = 60.dp, onClick = {onMinusClick()})
            }
        }
    }
}

@Preview
@Composable
fun prev() {
    SystemTheme {
       // WeeklyQuestCard(task = WeeklyQuest(quest = "Learn physics 10 times", currentTimesDone = 3, targetTimesDone =  10), onDoneClick = {})
    }
}