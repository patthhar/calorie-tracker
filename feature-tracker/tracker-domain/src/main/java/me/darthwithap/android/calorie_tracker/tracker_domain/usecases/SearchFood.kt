package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood
import me.darthwithap.android.calorie_tracker.tracker_domain.repository.TrackerRepository

class SearchFood(
  private val repository: TrackerRepository
) {
  suspend operator fun invoke(
    query: String,
    page: Int = 1,
    pageSize: Int = 40
  ): Result<List<TrackableFood>> {
    if (query.isBlank()) {
      return Result.success(emptyList())
    }
    return repository.searchFood(query.trim(), page, pageSize)
  }
}