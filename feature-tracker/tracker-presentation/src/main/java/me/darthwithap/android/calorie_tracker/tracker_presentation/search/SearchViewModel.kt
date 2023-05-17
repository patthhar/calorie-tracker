package me.darthwithap.android.calorie_tracker.tracker_presentation.search

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
import me.darthwithap.android.calorie_tracker.core.domain.usecases.FilterOutDigits
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core.util.UiText
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.TrackerDomainUseCases
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val trackerDomainUseCases: TrackerDomainUseCases,
  private val filterOutDigits: FilterOutDigits
) : ViewModel() {
  var state by mutableStateOf(SearchState())
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(event: SearchEvent) {
    when (event) {
      is SearchEvent.OnAmountForFoodChange -> {
        state = state.copy(
          trackableFoods = state.trackableFoods.map {
            if (it.food == event.food) {
              it.copy(amount = filterOutDigits.invoke(event.amount))
            } else it
          }
        )
      }

      is SearchEvent.OnQueryChange -> {
        state = state.copy(query = event.query)
      }

      is SearchEvent.OnSearch -> {
        executeSearch()
      }

      is SearchEvent.OnSearchFocusChange -> {
        state = state.copy(isHintVisible = !event.isFocused && state.query.isBlank())
      }

      is SearchEvent.OnToggleTrackableFood -> {
        state = state.copy(
          trackableFoods = state.trackableFoods.map {
            if (it.food == event.food) {
              it.copy(isExpanded = !it.isExpanded)
            } else it
          }
        )
      }

      is SearchEvent.OnTrackFoodClick -> {
        trackFood(event)
      }
    }
  }

  private fun executeSearch() {
    viewModelScope.launch {
      state = state.copy(isSearching = true, trackableFoods = emptyList())
      val result = trackerDomainUseCases.searchFood(state.query)
      result.onSuccess { trackableFoods ->
        state = state.copy(isSearching = false, query = "",
          trackableFoods = trackableFoods.map { TrackableFoodUiState(it) }
        )
      }
      result.onFailure {
        state = state.copy(isSearching = false)
        _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_something_went_wrong)))
      }
    }
  }

  private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
    viewModelScope.launch {
      val foodUiState = state.trackableFoods.find { it.food == event.food }
      trackerDomainUseCases.trackFood(
        foodUiState?.food ?: return@launch,
        foodUiState.amount.toIntOrNull() ?: return@launch,
        event.mealType,
        event.date
      )
      _uiEvent.send(UiEvent.NavigateUp)
    }
  }
}