package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import me.darthwithap.android.calorie_tracker.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
  private val repository: TrackerRepository
) {
  suspend operator fun invoke(trackedFood: TrackedFood) {
    repository.deleteTrackedFood(trackedFood)
  }
}