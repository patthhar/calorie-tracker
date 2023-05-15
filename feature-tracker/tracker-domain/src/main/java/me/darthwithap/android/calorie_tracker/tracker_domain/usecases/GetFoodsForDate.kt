package me.darthwithap.android.calorie_tracker.tracker_domain.usecases

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import me.darthwithap.android.calorie_tracker.tracker_domain.repository.TrackerRepository
import java.time.LocalDate

class GetFoodsForDate(
  private val repository: TrackerRepository
) {
  operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
    return repository.getFoodsForDate(date)
  }
}