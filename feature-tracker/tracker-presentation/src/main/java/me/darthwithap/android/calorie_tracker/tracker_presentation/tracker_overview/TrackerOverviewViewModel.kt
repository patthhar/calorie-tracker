package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.TrackerDomainUseCases
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
  prefs: Preferences,
  private val trackerDomainUseCases: TrackerDomainUseCases

) : ViewModel() {

  var state by mutableStateOf(TrackerOverviewState())
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  private var getFoodsForDate: Job? = null

  init {
    prefs.saveShouldShowOnboarding(false)
  }

  fun onEvent(event: TrackerOverviewEvent) {
    when (event) {
      is TrackerOverviewEvent.OnAddFoodClick -> {
        viewModelScope.launch {
          _uiEvent.send(
            UiEvent.Navigate(
              route = Route.Search
                  + "/${event.meal.mealType.name}"
                  + "/${state.date.dayOfMonth}"
                  + "/${state.date.monthValue}"
                  + "/${state.date.year}"
            )
          )
        }
      }

      is TrackerOverviewEvent.OnDeleteFoodClick -> {
        viewModelScope.launch {
          trackerDomainUseCases.deleteTrackedFood
            .invoke(event.trackedFood)
          refreshFoods()
        }
      }

      is TrackerOverviewEvent.OnNextDayClick -> {
        state = state.copy(
          date = state.date.plusDays(1)
        )
        refreshFoods()
      }

      is TrackerOverviewEvent.OnPreviousDayClick -> {
        state = state.copy(
          date = state.date.minusDays(1)
        )
        refreshFoods()
      }

      is TrackerOverviewEvent.OnToggleMealClick -> {
        state = state.copy(
          meals = state.meals.map {
            if (it.name == event.meal.name) {
              it.copy(
                isExpanded = !it.isExpanded
              )
            } else it
          }
        )
      }
    }
  }

  private fun refreshFoods() {
    getFoodsForDate?.cancel()
    getFoodsForDate = trackerDomainUseCases.getFoodsForDate(state.date)
      .onEach { trackedFoods ->
        val nutrientsResult = trackerDomainUseCases.calculateMealNutrients(
          trackedFoods
        )
        state = state.copy(
          totalCarbs = nutrientsResult.totalCarbs,
          totalProtein = nutrientsResult.totalFat,
          totalFat = nutrientsResult.totalFat,
          totalCalories = nutrientsResult.totalCalories,
          trackedFoods = trackedFoods,
          meals = state.meals.map {
            val nutrientsForMeal = nutrientsResult.mealNutrients[it.mealType]
              ?: return@map it.copy(
                carbs = 0,
                protein = 0,
                fat = 0,
                calories = 0
              )
            it.copy(
              carbs = nutrientsForMeal.carbs,
              protein = nutrientsForMeal.protein,
              fat = nutrientsForMeal.fat,
              calories = nutrientsForMeal.calories
            )
          }
        )
      }.launchIn(viewModelScope)
  }
}