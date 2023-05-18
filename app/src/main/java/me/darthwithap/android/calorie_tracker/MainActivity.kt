package me.darthwithap.android.calorie_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.navigation.navigate
import me.darthwithap.android.calorie_tracker.onboarding_presentation.activity.ActivityLevelScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.age.AgeScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.gender.GenderScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.goal.GoalScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.height.HeightScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.weight.WeightScreen
import me.darthwithap.android.calorie_tracker.onboarding_presentation.welcome.WelcomeScreen
import me.darthwithap.android.calorie_tracker.tracker_presentation.search.SearchScreen
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.TrackerOverviewScreen
import me.darthwithap.android.calorie_tracker.ui.theme.CalorieTrackerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var prefs: Preferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val shouldShowOnboarding = prefs.loadShouldShowOnboarding()

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
              startDestination = if (shouldShowOnboarding) Route.Welcome else Route.TrackerOverview
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
                NutrientGoalScreen(
                  scaffoldState = scaffoldState,
                  onNavigate = navController::navigate
                )
              }
              composable(
                route = Route.Search + "/{mealName}/{day}/{month}/{year}",
                arguments = listOf(
                  navArgument("mealName") {
                    type = NavType.StringType
                  },
                  navArgument("day") {
                    type = NavType.IntType
                  },
                  navArgument("month") {
                    type = NavType.IntType
                  },
                  navArgument("year") {
                    type = NavType.IntType
                  },
                )
              ) {
                val mealName = it.arguments?.getString("mealName")!!
                val day = it.arguments?.getInt("day")!!
                val month = it.arguments?.getInt("month")!!
                val year = it.arguments?.getInt("year")!!
                SearchScreen(
                  scaffoldState = scaffoldState,
                  mealName = mealName,
                  dayOfMonth = day,
                  month = month,
                  year = year
                ) {
                  navController.navigateUp()
                }
              }
              composable(Route.TrackerOverview) {
                TrackerOverviewScreen(onNavigate = navController::navigate)
              }
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