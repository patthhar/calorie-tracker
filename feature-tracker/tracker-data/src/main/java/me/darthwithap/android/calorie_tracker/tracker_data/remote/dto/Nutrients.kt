package me.darthwithap.android.calorie_tracker.tracker_data.remote.dto

import com.squareup.moshi.Json

data class Nutrients(
  @field:Json(name = "carbohydrates_100g")
  val carbohydratesPer100g: Double,
  @field:Json(name = "energy-kcal_100g")
  val caloriesPer100g: Double,
  @field:Json(name = "fat_100g")
  val fatPer100g: Double,
  @field:Json(name = "proteins_100g")
  val proteinsPer100g: Double
)
