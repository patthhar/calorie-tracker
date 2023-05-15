package me.darthwithap.android.calorie_tracker.tracker_domain.repository

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import java.time.LocalDate

interface TrackerRepository {
  suspend fun searchFood(query: String, page: Int, pageSize: Int): Result<List<TrackableFood>>

  suspend fun insertTrackedFood(food: TrackedFood)

  suspend fun deleteTrackedFood(food: TrackedFood)

  fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}