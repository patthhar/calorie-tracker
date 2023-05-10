package me.darthwithap.android.calorie_tracker.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.calorie_tracker.core.domain.models.Gender
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
  private val prefs: Preferences
) : ViewModel() {
  var selectedGender by mutableStateOf<Gender>(Gender.Male)
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onGenderClick(gender: Gender) {
    selectedGender = gender
  }

  fun onNextClick() {
    viewModelScope.launch {
      prefs.saveGender(selectedGender)
      // TODO: Add OnSuccess callback that will be passed in the Activity and then navigate
      _uiEvent.send(UiEvent.Navigate(Route.Age))
    }
  }

}