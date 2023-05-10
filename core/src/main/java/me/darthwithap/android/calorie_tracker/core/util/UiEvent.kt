package me.darthwithap.android.calorie_tracker.core.util

sealed class UiEvent {
  data class Navigate(val route: String) : UiEvent()
  object NavigateUp : UiEvent()
  data class ShowSnackBar(val msg: UiText) : UiEvent()
}
