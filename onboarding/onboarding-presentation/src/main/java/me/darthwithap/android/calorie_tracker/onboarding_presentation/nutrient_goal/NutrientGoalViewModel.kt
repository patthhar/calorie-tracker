package me.darthwithap.android.calorie_tracker.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.domain.usecases.FilterOutDigits
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.onboarding_domain.usecases.ValidateNutrientPercentages
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
  private val prefs: Preferences,
  private val filterOutDigits: FilterOutDigits,
  private val validateNutrientPercentages: ValidateNutrientPercentages
) : ViewModel() {
  var state by mutableStateOf(NutrientGoalState())
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(event: NutrientGoalEvent) {
    when (event) {
      is NutrientGoalEvent.OnCarbPercentageEnter -> {
        state = state.copy(carbPercentage = filterOutDigits(event.percent))
      }
      is NutrientGoalEvent.OnProteinPercentageEnter -> {
        state = state.copy(proteinPercentage = filterOutDigits(event.percent))
      }
      is NutrientGoalEvent.OnFatPercentageEnter -> {
        state = state.copy(fatPercentage = filterOutDigits(event.percent))
      }
      is NutrientGoalEvent.OnNextClick -> {
        //Verify that percentages are valid
        val result = validateNutrientPercentages.invoke(
          carbsPercentage = state.carbPercentage,
          proteinPercentage = state.proteinPercentage,
          fatPercentage = state.fatPercentage
        )
        when (result) {
          is ValidateNutrientPercentages.Result.Success -> {
            prefs.saveCarbPercentage(result.carbsPercent)
            prefs.saveProteinPercentage(result.proteinPercent)
            prefs.saveFatPercentage(result.fatPercent)

            viewModelScope.launch {
              _uiEvent.send(
                (UiEvent.Navigate(
                  Route.TrackerOverview
                ))
              )
            }
          }
          is ValidateNutrientPercentages.Result.Error -> {
            viewModelScope.launch {
              _uiEvent.send(UiEvent.ShowSnackBar(result.errorMsg))
            }
          }
        }
      }
    }
  }
}