package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import me.darthwithap.android.calorie_tracker.core.util.UiText
import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_presentation.R
import me.darthwithap.android.calorie_tracker.core.R as CoreR

data class Meal(
  val name: UiText,
  @DrawableRes val drawableRes: Int,
  val mealType: MealType,
  val carbs: Int = 0,
  val protein: Int = 0,
  val fat: Int = 0,
  val calories: Int = 0,
  val isExpanded: Boolean = false
)

val defaultMeals = listOf(
  Meal(
    name = UiText.StringResource(CoreR.string.breakfast),
    drawableRes = R.drawable.ic_breakfast,
    mealType = MealType.Breakfast
  ),
  Meal(
    name = UiText.StringResource(CoreR.string.lunch),
    drawableRes = R.drawable.ic_lunch,
    mealType = MealType.Lunch
  ),
  Meal(
    name = UiText.StringResource(CoreR.string.snacks),
    drawableRes = R.drawable.ic_snack,
    mealType = MealType.Snack
  ),
  Meal(
    name = UiText.StringResource(CoreR.string.dinner),
    drawableRes = R.drawable.ic_dinner,
    mealType = MealType.Dinner
  )
)
