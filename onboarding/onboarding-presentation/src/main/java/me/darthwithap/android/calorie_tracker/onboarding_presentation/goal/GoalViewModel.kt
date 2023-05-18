package me.darthwithap.android.calorie_tracker.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.calorie_tracker.core.domain.models.GoalType
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
  private val prefs: Preferences
) : ViewModel() {
  var selectedGoal by mutableStateOf<GoalType>(GoalType.LoseWeight)
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onGoalTypeClick(goal: GoalType) {
    selectedGoal = goal
  }

  fun onNextClick() {
    viewModelScope.launch {
      prefs.saveGoalType(selectedGoal)
      // TODO: Add OnSuccess callback that will be passed in the Activity and then navigate
      _uiEvent.send(UiEvent.NavigateOnSuccess)
    }
  }

}