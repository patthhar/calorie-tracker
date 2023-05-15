package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
  private val preferences: Preferences,
  private val calculateBmr: CalculateBmr,
  private val dailyCaloriesRequirement: DailyCaloriesRequirement
) {

  operator fun invoke(trackedFoods: List<TrackedFood>): Result {
    val mealWiseAllNutrients = trackedFoods
      .groupBy { it.mealType }
      .mapValues { entry ->
        val type = entry.key
        val foods = entry.value
        MealNutrients(
          carbs = foods.sumOf { it.carbs },
          fat = foods.sumOf { it.fats },
          protein = foods.sumOf { it.protein },
          calories = foods.sumOf { it.calories },
          mealType = type
        )
      }

    return with(mealWiseAllNutrients.values) {
      val totalCarbs = sumOf { it.carbs }
      val totalProtein = sumOf { it.protein }
      val totalFat = sumOf { it.fat }
      val totalCalories = sumOf { it.calories }

      val userInfo = preferences.loadUserInfo()

      val caloriesGoal = dailyCaloriesRequirement(userInfo)
      val carbsGoal = (caloriesGoal * userInfo.carbPercentage / 4f).roundToInt()
      val proteinGoal = (caloriesGoal * userInfo.proteinPercentage / 4f).roundToInt()
      val fatGoal = (caloriesGoal * userInfo.fatPercentage / 9f).roundToInt()

      return@with Result(
        carbsGoal = carbsGoal,
        proteinGoal = proteinGoal,
        fatGoal = fatGoal,
        totalCarbs = totalCarbs,
        caloriesGoal = caloriesGoal,
        totalProtein = totalProtein,
        totalFat = totalFat,
        totalCalories = totalCalories,
        mealNutrients = mealWiseAllNutrients
      )
    }

  }

  data class MealNutrients(
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val calories: Int,
    val mealType: MealType
  )

  data class Result(
    val carbsGoal: Int,
    val proteinGoal: Int,
    val fatGoal: Int,
    val caloriesGoal: Int,
    val totalCarbs: Int,
    val totalProtein: Int,
    val totalFat: Int,
    val totalCalories: Int,
    val mealNutrients: Map<MealType, MealNutrients>
  )
}