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
                ActivityLevelScreen(onNextClick = {
                  navController.navigate(Route.Goal)
                })
              }
              composable(Route.Age) {
                AgeScreen(
                  scaffoldState = scaffoldState,
                  onNextClick = {
                    navController.navigate(Route.Height)
                  }
                )
              }
              composable(Route.Gender) {
                GenderScreen(onNextClick = {
                  navController.navigate(Route.Age)
                })
              }
              composable(Route.Goal) {
                GoalScreen(onNextClick = {
                  navController.navigate(Route.NutrientGoal)
                })
              }
              composable(Route.Weight) {
                WeightScreen(
                  scaffoldState = scaffoldState,
                  onNextClick = {
                    navController.navigate(Route.Activity)
                  }
                )
              }
              composable(Route.Height) {
                HeightScreen(
                  scaffoldState = scaffoldState,
                  onNextClick = {
                    navController.navigate(Route.Weight)
                  }
                )
              }
              composable(Route.NutrientGoal) {
                NutrientGoalScreen(
                  scaffoldState = scaffoldState,
                  onNextClick = {
                    navController.navigate(Route.TrackerOverview)
                  }
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
                TrackerOverviewScreen(onNavigateToSearch = { mealName, day, month, year ->
                  navController.navigate(
                    Route.Search +
                        "/$mealName" + "/$day" + "/$month" + "/$year"
                  )
                })
              }
              composable(Route.Welcome) {
                WelcomeScreen(
                  onNextClick = {
                    navController.navigate(
                      Route.Gender
                    )
                  }
                )
              }
            }
          }
        )
      }
    }
  }
}