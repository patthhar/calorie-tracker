package me.darthwithap.android.calorie_tracker.core.domain.preferences

import me.darthwithap.android.calorie_tracker.core.domain.models.ActivityLevel
import me.darthwithap.android.calorie_tracker.core.domain.models.Gender
import me.darthwithap.android.calorie_tracker.core.domain.models.GoalType
import me.darthwithap.android.calorie_tracker.core.domain.models.UserInfo

interface Preferences {
  fun saveAge(age: Int)
  fun saveGender(gender: Gender)
  fun saveActivityLevel(level: ActivityLevel)
  fun saveGoalType(type: GoalType)
  fun saveHeight(height: Float)
  fun saveWeight(weight: Float)
  fun saveCarbPercentage(percentage: Float)
  fun saveProteinPercentage(percentage: Float)
  fun saveFatPercentage(percentage: Float)

  fun loadUserInfo(): UserInfo
  fun saveShouldShowOnboarding(show: Boolean)
  fun loadShouldShowOnboarding(): Boolean

  companion object {
    const val KEY_GENDER = "gender"
    const val KEY_AGE = "age"
    const val KEY_ACTIVITY_LEVEL = "activity_level"
    const val KEY_GOAL_TYPE = "goal_type"
    const val KEY_HEIGHT = "height"
    const val KEY_WEIGHT = "weight"
    const val KEY_CARB_PERCENTAGE = "carb_percentage"
    const val KEY_PROTEIN_PERCENTAGE = "protein_percentage"
    const val KEY_FAT_PERCENTAGE = "fat_percentage"
    const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
  }
}