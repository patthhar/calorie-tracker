package me.darthwithap.android.calorie_tracker.tracker_presentation.search

import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood

data class TrackableFoodUiState(
  val food: TrackableFood,
  val isExpanded: Boolean = false,
  val amount: String = ""
)
