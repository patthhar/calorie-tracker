package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

data class TrackerDomainUseCases(
  val trackFood: TrackFood,
  val searchFood: SearchFood,
  val getFoodsForDate: GetFoodsForDate,
  val deleteTrackedFood: DeleteTrackedFood,
  val calculateBmr: CalculateBmr,
  val calculateMealNutrients: CalculateMealNutrients
)
