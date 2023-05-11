package me.darthwithap.android.calorie_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.navigation.navigate
import me.darthwithap.android.calorie_tracker.onboarding_presentation.activity.ActivityLevelScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.age.AgeScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.gender.GenderScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.goal.GoalScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.height.HeightScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.weight.WeightScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.welcome.WelcomeScreen
import me.darthwithap.android.calorie_tracker.ui.theme.CalorieTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CalorieTrackerTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          scaffoldState = scaffoldState,
          content = { padding ->
            NavHost(
              modifier = Modifier.padding(padding),
              navController = navController,
              startDestination = Route.Welcome
            ) {
              composable(Route.Activity) {
                ActivityLevelScreen(onNavigate = navController::navigate)
              }
              composable(Route.Age) {
                AgeScreen(
                  scaffoldState = scaffoldState,
                  onNavigate = navController::navigate
                )
              }
              composable(Route.Gender) {
                GenderScreen(onNavigate = navController::navigate)
              }
              composable(Route.Goal) {
                GoalScreen(onNavigate = navController::navigate)
              }
              composable(Route.Weight) {
                WeightScreen(
                  scaffoldState = scaffoldState,
                  onNavigate = navController::navigate
                )
              }
              composable(Route.Height) {
                HeightScreen(
                  scaffoldState = scaffoldState,
                  onNavigate = navController::navigate
                )
              }
              composable(Route.NutrientGoal) {

              }
              composable(Route.Search) {}
              composable(Route.TrackerOverview) {}
              composable(Route.Welcome) {
                WelcomeScreen(onNavigate = navController::navigate)
              }
            }
          }
        )
      }
    }
  }
}