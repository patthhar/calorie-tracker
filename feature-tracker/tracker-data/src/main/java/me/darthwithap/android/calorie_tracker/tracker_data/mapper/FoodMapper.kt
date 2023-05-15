package me.darthwithap.android.calorie_tracker.tracker_data.mapper

import me.darthwithap.android.calorie_tracker.tracker_data.local.entity.TrackedFoodEntity
import me.darthwithap.android.calorie_tracker.tracker_data.remote.dto.Product
import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import java.time.LocalDate
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
  val caloriesPer100g = nutrients.caloriesPer100g.roundToInt()
  val carbsPer100g = nutrients.carbohydratesPer100g.roundToInt()
  val proteinPer100g = nutrients.proteinsPer100g.roundToInt()
  val fatPer100g = nutrients.fatPer100g.roundToInt()

  return TrackableFood(
    name = productName ?: return null,
    carbsPer100gm = carbsPer100g,
    caloriesPer100gm = caloriesPer100g,
    fatsPer100gm = fatPer100g,
    imageUrl = imageFrontThumbUrl,
    proteinsPer100gm = proteinPer100g
  )
}

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
  return TrackedFood(
    name = name,
    carbs = carbs,
    protein = protein,
    fats = fat,
    imageUrl = imageUrl,
    mealType = MealType.fromString(mealType),
    amountInGms = amountInGms,
    calories = calories,
    date = LocalDate.of(year, month, dayOfMonth),
    id = id
  )
}

fun TrackedFood.toEntity(): TrackedFoodEntity {
  return TrackedFoodEntity(
    name = name,
    carbs = carbs,
    protein = protein,
    fat = fats,
    imageUrl = imageUrl,
    mealType = mealType.name,
    amountInGms = amountInGms,
    calories = calories,
    dayOfMonth = date.dayOfMonth,
    month = date.monthValue,
    year = date.year,
    id = id
  )
}