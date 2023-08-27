package me.darthwithap.android.calorie_tracker.onboarding_presentation.age

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
class AgeViewModel @Inject constructor(
  private val prefs: Preferences,
  private val filterOutDigits: FilterOutDigits
) : ViewModel() {
  var age by mutableStateOf("20")
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onAgeEnter(enteredAge: String) {
    if (enteredAge.length < 3) {
      age = filterOutDigits(enteredAge)
    }
  }

  fun onNextClick() {
    viewModelScope.launch {
      val ageNumber = age.toIntOrNull() ?: kotlin.run {
        _uiEvent.send(
          UiEvent.ShowSnackBar(
            UiText.StringResource(R.string.error_age_cant_be_empty)
          )
        )
        return@launch
      }
      if (ageNumber <= 0) {
        _uiEvent.send(
          UiEvent.ShowSnackBar(
            UiText.StringResource(R.string.error_age_cant_be_negative)
          )
        )
        return@launch
      }
      prefs.saveAge(ageNumber)
      _uiEvent.send(UiEvent.NavigateOnSuccess)
    }
  }
}