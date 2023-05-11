package me.darthwithap.android.calorie_tracker.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.core.domain.usecases.FilterOutDigits
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core.util.UiText
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
  private val prefs: Preferences,
  private val filterOutDigits: FilterOutDigits
) : ViewModel() {
  var height by mutableStateOf("175.0")
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onHeightEnter(enteredHeight: String) {
    if (enteredHeight.length < 6) {
      height = enteredHeight
    }
  }

  fun onNextClick() {
    viewModelScope.launch {
      val heightNumber = height.toFloatOrNull() ?: kotlin.run {
        _uiEvent.send(
          UiEvent.ShowSnackBar(
            UiText.StringResource(R.string.error_height_cant_be_empty)
          )
        )
        return@launch
      }
      prefs.saveHeight(heightNumber)
      _uiEvent.send(UiEvent.Navigate(Route.Weight))
    }
  }
}