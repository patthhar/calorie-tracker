package me.darthwithap.android.calorie_tracker.tracker_presentation.search

import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
  data class OnQueryChange(val query: String) : SearchEvent()
  object OnSearch : SearchEvent()
  data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
  data class OnAmountForFoodChange(val amount: String, val food: TrackableFood) : SearchEvent()
  data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent()
  data class OnTrackFoodClick(
    val food: TrackableFood,
    val mealType: MealType,
    val date: LocalDate
  ) : SearchEvent()
}
