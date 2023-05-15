package me.darthwithap.android.calorie_tracker.tracker_domain.models

import java.time.LocalDate

data class TrackedFood(
  val name: String,
  val carbs: Int,
  val protein: Int,
  val fats: Int,
  val imageUrl: String?,
  val mealType: MealType,
  val amountInGms: Int,
  val date: LocalDate,
  val calories: Int,
  val id: Int? = null
)
