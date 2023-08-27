package me.darthwithap.android.calorie_tracker.tracker_domain.models

sealed class MealType(val name: String) {
  object Breakfast : MealType("breakfast")
  object Lunch : MealType("lunch")
  object Snacks : MealType("snacks")
  object Dinner : MealType("dinner")

  companion object {
    fun fromString(name: String): MealType {
      return when (name) {
        "breakfast" -> Breakfast
        "lunch" -> Lunch
        "snacks" -> Snacks
        "dinner" -> Dinner
        else -> Breakfast
      }
    }
  }
}
