package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import me.darthwithap.android.calorie_tracker.core.domain.models.Gender
import me.darthwithap.android.calorie_tracker.core.domain.models.UserInfo
import kotlin.math.roundToInt

class CalculateBmr {
  operator fun invoke(
    userInfo: UserInfo
  ): Int {
    return when (userInfo.gender) {
      is Gender.Male -> {
        (66.47f + 13.75f * userInfo.weight +
            5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
      }

      is Gender.Female -> {
        (665.09f + 9.56 * userInfo.weight +
            1.84 * userInfo.height - 4.67f * userInfo.age).roundToInt()
      }
      //Will never be reached
      else -> -1
    }
  }
}