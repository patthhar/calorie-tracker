package me.darthwithap.android.calorie_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.navigation.navigate
import me.darthwithap.android.calorie_tracker.onboarding_presentation.gender.GenderScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.welcome.WelcomeScreen
import me.darthwithap.android.calorie_tracker.ui.theme.CalorieTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CalorieTrackerTheme {
        val navController = rememberNavController()
        NavHost(
          navController = navController,
          startDestination = Route.Welcome
        ) {
          composable(Route.Activity) {}
          composable(Route.Age) {
            Text(text = "Age")
          }
          composable(Route.Gender) {
            GenderScreen(onNavigate = navController::navigate)
          }
          composable(Route.Goal) {}
          composable(Route.Height) {}
          composable(Route.NutrientGoal) {}
          composable(Route.Search) {}
          composable(Route.TrackerOverview) {}
          composable(Route.Welcome) {
            WelcomeScreen(onNavigate = navController::navigate)
          }
        }
      }
    }
  }
}