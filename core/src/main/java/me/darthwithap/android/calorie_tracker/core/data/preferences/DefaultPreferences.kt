package me.darthwithap.android.calorie_tracker.core.data.preferences

import android.content.SharedPreferences
import me.darthwithap.android.calorie_tracker.core.domain.models.ActivityLevel
import me.darthwithap.android.calorie_tracker.core.domain.models.Gender
import me.darthwithap.android.calorie_tracker.core.domain.models.GoalType
import me.darthwithap.android.calorie_tracker.core.domain.models.UserInfo
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences

class DefaultPreferences(
  private val sharedPrefs: SharedPreferences
) : Preferences {
  override fun saveAge(age: Int) {
    sharedPrefs.edit().putInt(Preferences.KEY_AGE, age).apply()
  }

  override fun saveGender(gender: Gender) {
    sharedPrefs.edit().putString(Preferences.KEY_GENDER, gender.name).apply()
  }

  override fun saveActivityLevel(level: ActivityLevel) {
    sharedPrefs.edit().putString(Preferences.KEY_ACTIVITY_LEVEL, level.name).apply()
  }

  override fun saveGoalType(type: GoalType) {
    sharedPrefs.edit().putString(Preferences.KEY_GOAL_TYPE, type.name).apply()
  }

  override fun saveHeight(height: Float) {
    sharedPrefs.edit().putFloat(Preferences.KEY_HEIGHT, height).apply()
  }

  override fun saveWeight(weight: Float) {
    sharedPrefs.edit().putFloat(Preferences.KEY_WEIGHT, weight).apply()
  }

  override fun saveCarbPercentage(percentage: Float) {
    sharedPrefs.edit().putFloat(Preferences.KEY_CARB_PERCENTAGE, percentage).apply()
  }

  override fun saveProteinPercentage(percentage: Float) {
    sharedPrefs.edit().putFloat(Preferences.KEY_PROTEIN_PERCENTAGE, percentage).apply()
  }

  override fun saveFatPercentage(percentage: Float) {
    sharedPrefs.edit().putFloat(Preferences.KEY_FAT_PERCENTAGE, percentage).apply()
  }

  override fun loadUserInfo(): UserInfo {
    sharedPrefs.apply {
      val userInfo = with(Preferences) {
        return@with UserInfo(
          gender = Gender.fromString(getString(KEY_GENDER, null) ?: Gender.Male.name),
          age = getInt(KEY_AGE, 0),
          weight = getFloat(KEY_WEIGHT, 0f),
          height = getFloat(KEY_HEIGHT, 0f),
          activityLevel = ActivityLevel.fromString(
            getString(KEY_ACTIVITY_LEVEL, null) ?: ActivityLevel.Low.name
          ),
          goalType = GoalType.fromString(
            getString(KEY_GOAL_TYPE, null) ?: GoalType.LoseWeight.name
          ),
          carbPercentage = getFloat(KEY_CARB_PERCENTAGE, 65f),
          proteinPercentage = getFloat(KEY_PROTEIN_PERCENTAGE, 15f),
          fatPercentage = getFloat(KEY_FAT_PERCENTAGE, 20f)
        )
      }
      return userInfo
    }
  }
}