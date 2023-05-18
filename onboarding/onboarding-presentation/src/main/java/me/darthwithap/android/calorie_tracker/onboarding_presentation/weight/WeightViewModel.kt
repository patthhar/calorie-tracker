package me.darthwithap.android.calorie_tracker.onboarding_presentation.weight

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
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core.util.UiText
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
  private val prefs: Preferences,
) : ViewModel() {
  var weight by mutableStateOf("80.0")
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onWeightEnter(enteredWeight: String) {
    if (enteredWeight.length < 6) {
      weight = enteredWeight
    }
  }

  fun onNextClick() {
    viewModelScope.launch {
      val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
        _uiEvent.send(
          UiEvent.ShowSnackBar(
            UiText.StringResource(R.string.error_weight_cant_be_empty)
          )
        )
        return@launch
      }
      prefs.saveWeight(weightNumber)
      _uiEvent.send(UiEvent.NavigateOnSuccess)
    }
  }
}