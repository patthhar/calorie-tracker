package me.darthwithap.android.calorie_tracker.onboarding_domain.usecases

import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.util.UiText

class ValidateNutrientPercentages {
  operator fun invoke(
    carbsPercentage: String,
    proteinPercentage: String,
    fatPercentage: String
  ): Result {
    val carbs = carbsPercentage.toIntOrNull()
    val protein = proteinPercentage.toIntOrNull()
    val fat = fatPercentage.toIntOrNull()

    if (carbs == null || protein == null || fat == null) {
      return Result.Error(errorMsg = UiText.StringResource(R.string.error_invalid_values))
    }
    if (carbs + protein + fat != 100) {
      return Result.Error(errorMsg = UiText.StringResource(R.string.error_not_100_percent))
    }
    return Result.Success(
      carbs / 100f,
      fat / 100f,
      protein / 100f
    )
  }

  sealed class Result {
    data class Success(val carbsPercent: Float, val proteinPercent: Float, val fatPercent: Float) :
      Result()

    data class Error(val errorMsg: UiText) : Result()
  }
}