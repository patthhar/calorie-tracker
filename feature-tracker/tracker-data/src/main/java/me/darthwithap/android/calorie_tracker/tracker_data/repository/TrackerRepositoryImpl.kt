package me.darthwithap.android.calorie_tracker.tracker_data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.darthwithap.android.calorie_tracker.tracker_data.local.TrackerDao
import me.darthwithap.android.calorie_tracker.tracker_data.local.TrackerDatabase
import me.darthwithap.android.calorie_tracker.tracker_data.mapper.toEntity
import me.darthwithap.android.calorie_tracker.tracker_data.mapper.toTrackableFood
import me.darthwithap.android.calorie_tracker.tracker_data.mapper.toTrackedFood
import me.darthwithap.android.calorie_tracker.tracker_data.remote.OpenFoodApi
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackableFood
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import me.darthwithap.android.calorie_tracker.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor(
  private val api: OpenFoodApi,
  private val db: TrackerDatabase
) : TrackerRepository {
  private val dao = db.dao
  override suspend fun searchFood(
    query: String,
    page: Int,
    pageSize: Int
  ): Result<List<TrackableFood>> {

    return try {
      val searchDto = api.searchFood(query, page, pageSize)
      Result.success(searchDto.products.mapNotNull {
        it.toTrackableFood()
      })
    } catch (e: Exception) {
      e.printStackTrace()
      Result.failure(e)
    }
  }

  override suspend fun insertTrackedFood(food: TrackedFood) {
    dao.upsertTrackedFood(food.toEntity())
  }

  override suspend fun deleteTrackedFood(food: TrackedFood) {
    dao.deleteTrackedFood(food.toEntity())
  }

  override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
    return dao.getFoodsForDate(localDate.dayOfMonth, localDate.monthValue, localDate.year)
      .map { entities ->
        entities.map {
          it.toTrackedFood()
        }
      }
  }
}