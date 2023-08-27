package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components.AddButton
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components.DaySelector
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components.NutrientsHeader
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components.TrackedFoodItem

@Composable
fun TrackerOverviewScreen(
  onNavigateToSearch: (String, Int, Int, Int) -> Unit,
  viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
  val dimens = LocalDimensions.current
  val state = viewModel.state
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(bottom = dimens.base)
  ) {
    item {
      NutrientsHeader(state = state)
      Spacer(modifier = Modifier.height(dimens.base))
      DaySelector(
        date = state.date,
        onPreviousDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick) },
        onNextDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = dimens.big)
      )
      Spacer(modifier = Modifier.height(dimens.base))
    }
    items(state.meals) { meal ->
      ExpandableMeal(
        meal = meal,
        onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal)) },
        content = {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = dimens.xs)
          ) {
            val foodsForMeal = state.trackedFoods.filter { it.mealType == meal.mealType }
            foodsForMeal.forEach { food ->
              TrackedFoodItem(trackedFood = food, onDeleteClick = {
                viewModel.onEvent(TrackerOverviewEvent.OnDeleteFoodClick(food))
              })
              Spacer(modifier = Modifier.height(dimens.small))
            }
            AddButton(
              text = stringResource(id = R.string.add_meal, meal.name.asString(context)),
              onClick = {
                onNavigateToSearch(
                  meal.name.asString(context).lowercase(),
                  state.date.dayOfMonth,
                  state.date.monthValue,
                  state.date.year
                )
              },
              modifier = Modifier.fillMaxWidth()
            )
          }
        },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}