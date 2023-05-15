package me.darthwithap.android.calorie_tracker.tracker_domain.models

data class TrackableFood(
  val name: String,
  val imageUrl: String?,
  val caloriesPer100gm: Int,
  val carbsPer100gm: Int,
  val proteinsPer100gm: Int,
  val fatsPer100gm: Int,
)
