package me.darthwithap.android.calorie_tracker.core.domain.models

sealed class ActivityLevel(val name: String) {
  object Low : ActivityLevel("low_activity")
  object Medium : ActivityLevel("medium_activity")
  object High : ActivityLevel("high_activity")

  companion object {
    fun fromString(name: String): ActivityLevel {
      return when (name) {
        "low_activity" -> Low
        "medium_activity" -> Medium
        "high_activity" -> High
        else -> Low
      }
    }
  }
}
