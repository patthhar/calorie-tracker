package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import me.darthwithap.android.calorie_tracker.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
  private val repository: TrackerRepository
) {
  suspend operator fun invoke(
    food: TrackableFood,
    amountInGms: Int,
    mealType: MealType,
    date: LocalDate
  ) {
    val trackedFood = with(food) {
      return@with TrackedFood(
        name = name,
        carbs = ((carbsPer100gm / 100f) * amountInGms).roundToInt(),
        protein = ((proteinsPer100gm / 100f) * amountInGms).roundToInt(),
        fats = ((fatsPer100gm / 100f) * amountInGms).roundToInt(),
        mealType = mealType,
        imageUrl = imageUrl,
        amountInGms = amountInGms,
        date = date,
        calories = ((caloriesPer100gm / 100f) * amountInGms).roundToInt()
      )
    }
    repository.upsertTrackedFood(trackedFood)
  }
}