package me.darthwithap.android.calorie_tracker.core.util

sealed class UiEvent {
  object NavigateOnSuccess : UiEvent()
  object NavigateUp : UiEvent()
  data class ShowSnackBar(val msg: UiText) : UiEvent()
}
