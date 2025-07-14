package com.example.system.presentation.weekly_quests

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.example.system.R
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.WeeklyQuest
import com.example.system.presentation.common.DailyQuestCard
import com.example.system.presentation.common.GlowingText
import com.example.system.presentation.common.WeeklyQuestCard
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.flow.Flow


@SuppressLint("UnrememberedMutableState")
@Composable
fun WeeklyQuestsScreen(
    quests: List<WeeklyQuest>,
    event: (WeeklyQuestsEvents) -> Unit,
    toastFlow: Flow<String>
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        toastFlow.collect { message ->
            StyleableToast
                .Builder(context)
                .text(message)
                .textColor(Color.WHITE)
                .backgroundColor(0xFF242930.toInt())
                .solidBackground()
                .show()
        }
    }

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

                        val progress by derivedStateOf {
                                quests[card].currentTimesDone.toFloat() / quests[card].targetTimesDone.toFloat()
                        }

                        WeeklyQuestCard(
                            task = quests[card],
                            onPlusClick = {
                                event(WeeklyQuestsEvents.OnPlusClicked(quests[card]))
                            },
                            onMinusClick = {
                                event(WeeklyQuestsEvents.OnMinusClicked(quests[card]))
                            },
                            progress = progress
                        )
                    }
                }
            }
        }
    }
}