package me.darthwithap.android.calorie_tracker.core.domain.models

data class UserInfo(
  val gender: Gender,
  val age: Int,
  val weight: Float,
  val height: Float,
  val activityLevel: ActivityLevel,
  val goalType: GoalType,
  val carbPercentage: Float,
  val proteinPercentage: Float,
  val fatPercentage: Float
)
