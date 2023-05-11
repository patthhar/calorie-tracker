package me.darthwithap.android.calorie_tracker.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
  data class OnCarbPercentageEnter(val percent: String) : NutrientGoalEvent()
  data class OnProteinPercentageEnter(val percent: String) : NutrientGoalEvent()
  data class OnFatPercentageEnter(val percent: String) : NutrientGoalEvent()
  object OnNextClick : NutrientGoalEvent()
}