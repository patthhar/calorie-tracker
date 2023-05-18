package me.darthwithap.android.calorie_tracker.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.calorie_tracker.core.domain.models.ActivityLevel
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
  private val prefs: Preferences
) : ViewModel() {
  var selectedActivity by mutableStateOf<ActivityLevel>(ActivityLevel.Low)
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onActivityLevelClick(level: ActivityLevel) {
    selectedActivity = level
  }

  fun onNextClick() {
    viewModelScope.launch {
      prefs.saveActivityLevel(selectedActivity)
      // TODO: Add OnSuccess callback that will be passed in the Activity and then navigate
      _uiEvent.send(UiEvent.NavigateOnSuccess)
    }
  }

}