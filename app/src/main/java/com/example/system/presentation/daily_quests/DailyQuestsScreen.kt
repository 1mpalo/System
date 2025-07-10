package com.example.system.presentation.daily_quests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.system.R
import com.example.system.domain.model.DailyQuest
import com.example.system.presentation.common.GlowingText
import com.example.system.presentation.common.DailyQuestCard


@Composable
fun DailyQuestsScreen(
    quests: List<DailyQuest>,
    onDoneClicked: (DailyQuest) -> Unit
) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                if (quests.isEmpty()) {
                    item {
                        GlowingText(
                            modifier = Modifier.fillMaxSize(),
                            text = "Everything is done",
                            fontSize = MaterialTheme.typography.displayLarge.fontSize,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(quests.size) { card ->
                        DailyQuestCard(
                            task = quests[card],
                            onDoneClick = {
                                onDoneClicked(quests[card])
                            }
                        )
                    }
                }
            }
        }
    }
}