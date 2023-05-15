package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import me.darthwithap.android.calorie_tracker.core.domain.models.ActivityLevel
import me.darthwithap.android.calorie_tracker.core.domain.models.GoalType
import me.darthwithap.android.calorie_tracker.core.domain.models.UserInfo
import kotlin.math.roundToInt

class DailyCaloriesRequirement(
  private val calculateBmr: CalculateBmr
) {
  operator fun invoke(userInfo: UserInfo): Int {
    val activityFactor = when (userInfo.activityLevel) {
      is ActivityLevel.Low -> 1.2f
      is ActivityLevel.Medium -> 1.3f
      is ActivityLevel.High -> 1.4f
    }
    val extraCalories = when (userInfo.goalType) {
      is GoalType.LoseWeight -> -500
      is GoalType.KeepWeight -> 0
      is GoalType.GainWeight -> 500
    }
    return (calculateBmr.invoke(userInfo) * activityFactor + extraCalories).roundToInt()
  }
}