package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import me.darthwithap.android.calorie_tracker.core.domain.models.ActivityLevel
import me.darthwithap.android.calorie_tracker.core.domain.models.Gender
import me.darthwithap.android.calorie_tracker.core.domain.models.GoalType
import me.darthwithap.android.calorie_tracker.core.domain.models.UserInfo
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {
  private lateinit var calculateMealNutrients: CalculateMealNutrients
  private lateinit var userInfo: UserInfo

  private lateinit var dailyCaloriesRequirement: DailyCaloriesRequirement
  private lateinit var preferences: Preferences

  @Before
  fun setUp() {
    preferences = mockk<Preferences>(relaxed = true)
    every { preferences.loadUserInfo() } returns UserInfo(
      Gender.Male,
      30,
      70f,
      175f,
      ActivityLevel.Medium,
      GoalType.LoseWeight,
      40f,
      30f,
      30f
    )
    dailyCaloriesRequirement = mockk<DailyCaloriesRequirement>()
    every { dailyCaloriesRequirement.invoke(userInfo) } returns 2000
    calculateMealNutrients = CalculateMealNutrients(preferences, dailyCaloriesRequirement)
  }

  @Test
  fun `test meal nutrient calculation`() {
    val trackedFoods = (1..20).map {
      TrackedFood(
        "Food",
        Random.nextInt(100),
        Random.nextInt(40),
        Random.nextInt(80),
        null,
        MealType.fromString(
          listOf("breakfast", "lunch", "snack", "dinner").random()
        ),
        10,
        LocalDate.now(),
        Random.nextInt(700)
      )
    }

    // Invoke the CalculateMealNutrients function
    val result = calculateMealNutrients.invoke(trackedFoods)
    val expectedResult =
      trackedFoods.filter { it.mealType is MealType.Breakfast }.sumOf { it.calories }
    val breakfastCalories =
      result.mealNutrients.filter { it.key is MealType.Breakfast }.values.sumOf { it.calories }

    assertThat(breakfastCalories).isEqualTo(expectedResult)
  }

  @After
  fun tearDown() {

  }
}