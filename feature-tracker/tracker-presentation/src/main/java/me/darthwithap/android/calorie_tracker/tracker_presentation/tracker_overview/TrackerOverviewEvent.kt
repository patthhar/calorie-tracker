package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview

import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood

sealed class TrackerOverviewEvent {
  object OnNextDayClick : TrackerOverviewEvent()
  object OnPreviousDayClick : TrackerOverviewEvent()
  data class OnToggleMealClick(val meal: Meal) : TrackerOverviewEvent()
  data class OnAddFoodClick(val meal: Meal) : TrackerOverviewEvent()
  data class OnDeleteFoodClick(val trackedFood: TrackedFood) : TrackerOverviewEvent()
}