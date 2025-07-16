package com.example.system.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.system.R
import com.example.system.domain.model.Title
import com.example.system.presentation.common.GlowingText
import com.example.system.presentation.common.NotificationNoIcon
import com.example.system.presentation.common.Parameter
import com.example.system.presentation.common.TitleItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.roundToInt

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    event: (HomeScreenEvents) -> Unit,
    titles: List<Title>
) {

    val expForNextLvl by derivedStateOf {
        ((state.level.toDouble().pow(1.5)) * 20).roundToInt()
    }

    val prevLevelsXp by derivedStateOf {
        (((state.level - 1).toDouble().pow(1.5)) * 20).roundToInt()
    }

    val currentExp by derivedStateOf {
        val value = ((state.strength * 2) +
                (state.agility * 2) +
                (state.intelligence * 2) +
                (state.vitality * 2)).toInt()

        value.coerceIn(null, expForNextLvl)
    }

    if(currentExp == expForNextLvl) {
        event(HomeScreenEvents.UpdateLevel)
    }

    val progress by derivedStateOf {
        val progressValue = ((currentExp - prevLevelsXp) / (expForNextLvl - prevLevelsXp).toFloat())
        Log.d("progress", "$progressValue, $currentExp, $prevLevelsXp, $expForNextLvl")
        progressValue.coerceIn(0f, 1f)
    }
    Log.d("weird","${progress}")
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.35f)) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Menu",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Daily quests",
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = false,
                        onClick = { event(HomeScreenEvents.OnDailyQuestClicked) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Weekly quests",
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = false,
                        onClick = { event(HomeScreenEvents.OnWeeklyQuestClicked) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Logs",
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = false,
                        onClick = { event(HomeScreenEvents.OnLogsQuestClicked) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Settings",
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = false,
                        onClick = { event(HomeScreenEvents.OnSettingsQuestClicked) }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NotificationNoIcon(text = "STATUS")

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    Box(
                        modifier = Modifier.width(500.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 80.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                GlowingText(
                                    text = "${state.level}",
                                    fontSize = MaterialTheme.typography.displayLarge.fontSize
                                )
                                GlowingText(
                                    text = "LEVEL",
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                                )
                            }
                            Spacer(modifier = Modifier.width(40.dp))
                            Column(modifier = Modifier.padding(bottom = 5.dp)) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .height(18.dp)
                                            .fillMaxWidth(0.75f),
                                        progress = { progress },
                                        gapSize = (-30).dp
                                    )

                                    GlowingText(
                                        modifier = Modifier
                                            .fillMaxWidth(0.75f)
                                            .padding(if (progress.toFloat() == 1f) 5.dp else 17.dp),
                                        text = "${currentExp}/${expForNextLvl}",
                                        textAlign = TextAlign.End,
                                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                TitleItem(
                                    title = "Title: ${state.title}",
                                    dropDownItems = titles,
                                    onItemClicked = { title -> event(HomeScreenEvents.OnTitleClicked(title)) }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Box(
                        modifier = Modifier
                            .width(550.dp)
                            .height(150.dp)
                            .border(0.4.dp, Color.White)
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Левая колонка
                            Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Parameter(
                                    painter = painterResource(R.drawable.ic_strength),
                                    text = "STR",
                                    textFontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    pointsFontSize = MaterialTheme.typography.displayMedium.fontSize,
                                    points = state.strength
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Parameter(
                                    painter = painterResource(R.drawable.ic_agility),
                                    text = "AGI",
                                    textFontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    pointsFontSize = MaterialTheme.typography.displayMedium.fontSize,
                                    points = state.agility
                                )
                            }
                            Spacer(modifier = Modifier.width(100.dp))
                            // Правая колонка
                            Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Parameter(
                                    painter = painterResource(R.drawable.ic_vitality),
                                    text = "VIT",
                                    textFontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    pointsFontSize = MaterialTheme.typography.displayMedium.fontSize,
                                    points = state.vitality
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Parameter(
                                    painter = painterResource(R.drawable.ic_intelligence),
                                    text = "INT",
                                    textFontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    pointsFontSize = MaterialTheme.typography.displayMedium.fontSize,
                                    points = state.intelligence
                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 30.dp, top = 25.dp)
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                        Icon(
                            tint = Color.White,
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
