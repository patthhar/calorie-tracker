package me.darthwithap.android.calorie_tracker.core.domain.models

//Required name property to save data in sharedPreferences
sealed class Gender(val name: String) {
  object Male : Gender("male")
  object Female : Gender("female")

  companion object {
    fun fromString(name: String): Gender {
      return when (name) {
        "male" -> Male
        "female" -> Female
        else -> Male
      }
    }
  }
}
