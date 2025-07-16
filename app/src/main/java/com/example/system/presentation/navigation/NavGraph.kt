package com.example.system.presentation.navigation

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.system.MainViewModel
import com.example.system.domain.model.WeeklyQuest
import com.example.system.presentation.daily_quests.DailyQuestsScreen
import com.example.system.presentation.daily_quests.DailyQuestsViewModel
import com.example.system.presentation.home.HomeScreen
import com.example.system.presentation.home.HomeScreenViewModel
import com.example.system.presentation.home.HomeState
import com.example.system.presentation.home.WelcomingScreen
import com.example.system.presentation.onboarding.OnBoardingScreen
import com.example.system.presentation.onboarding.OnBoardingViewModel
import com.example.system.presentation.weekly_quests.WeeklyQuestsScreen
import com.example.system.presentation.weekly_quests.WeeklyQuestsViewModel
import com.example.system.ui.theme.SystemTheme
import com.example.system.unil.Constants.dailyQuests
import kotlin.collections.removeLastOrNull
import kotlin.getValue
import kotlin.system.exitProcess

@Composable
fun NavGraph(startDestination: Screen) {


    Log.d("startdasdas", "$startDestination")

    val backStack = remember {
        mutableStateListOf<Screen>(startDestination)
    }


    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<Screen.OnBoardingScreen> {
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()

                OnBoardingScreen(
                    onYesClicked = {
                        onBoardingViewModel.saveEntry()
                        backStack.clear()
                        backStack.add(Screen.WelcomingScreen)
                        Log.d("startdasdas", "$startDestination")
                    },
                    onNoClicked = {
                        exitProcess(0)
                    }
                )
            }

            entry<Screen.WelcomingScreen>(
                metadata = NavDisplay.transitionSpec {
                    fadeIn(
                        animationSpec = keyframes {
                            durationMillis = 2650
                            0.0f at 0 // старт
                            0.2f at 1000 // первый пик
                            0.0f at 1700 // снова исчез
                            0.2f at 2200 // снова появился
                            0.4f at 2400
                            1.0f at 2650
                        }
                    ) togetherWith
                            fadeOut()
                }
            ) {
                WelcomingScreen(onContinueClick = {
                    backStack.clear()
                    backStack.add(Screen.HomeScreen)
                })
            }

            entry<Screen.HomeScreen> {
                val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
                val state by homeScreenViewModel.state.collectAsState()
                val  titles by homeScreenViewModel.titles.collectAsState()
                HomeScreen(
                    state = state,
                    event = {homeScreenViewModel.onEvent(events = it,backStack)},
                    titles = titles
                )
            }

            entry<Screen.DailyQuests>(
                metadata = NavDisplay.transitionSpec {
                    slideInHorizontally() togetherWith fadeOut()
                }
            ) {
                val dailyQuestsViewModel: DailyQuestsViewModel = hiltViewModel()
                val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
                val quests by dailyQuestsViewModel.quests.collectAsState()
                DailyQuestsScreen(
                    quests = quests,
                    onDoneClicked = { quest ->
                        val numOfPoints: Int = quest.points[0].digitToInt()
                        val stat: String = quest.points.substringAfterLast(" ")

                        dailyQuestsViewModel.onDoneClicked(
                            currentStats = homeScreenViewModel.state.value,
                            stat = stat,
                            points = numOfPoints,
                            quest = quest
                        )
                    }
                )
            }

            entry<Screen.WeeklyQuests>(
                metadata = NavDisplay.transitionSpec {
                    slideInHorizontally() togetherWith fadeOut()
                }
            ) {
                val weeklyQuestsViewModel: WeeklyQuestsViewModel = hiltViewModel()
                val quests by weeklyQuestsViewModel.quests.collectAsState()
                val toastFlow = weeklyQuestsViewModel.toastFlow
                WeeklyQuestsScreen(
                    quests = quests,
                    event = weeklyQuestsViewModel::onEvent,
                    toastFlow = toastFlow
                )
            }
        }
    )
}
