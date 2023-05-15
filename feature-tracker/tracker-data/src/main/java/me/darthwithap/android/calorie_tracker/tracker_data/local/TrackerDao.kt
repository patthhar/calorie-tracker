package me.darthwithap.android.calorie_tracker.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.calorie_tracker.tracker_data.local.entity.TrackedFoodEntity

@Dao
interface TrackerDao {

  @Upsert
  suspend fun upsertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

  @Delete
  suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

  @Query(
    """
      SELECT * FROM trackedfoodentity
      WHERE dayOfMonth =:day AND month = :month AND year = :year
    """
  )
  fun getFoodsForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>

}