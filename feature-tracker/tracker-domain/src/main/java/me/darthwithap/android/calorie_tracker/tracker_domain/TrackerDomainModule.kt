package me.darthwithap.android.calorie_tracker.tracker_domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import me.darthwithap.android.calorie_tracker.tracker_domain.repository.TrackerRepository
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.CalculateBmr
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.CalculateMealNutrients
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.DailyCaloriesRequirement
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.DeleteTrackedFood
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.GetFoodsForDate
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.SearchFood
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.TrackFood
import me.darthwithap.android.calorie_tracker.tracker_domain.usecases.TrackerDomainUseCases

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

  @Provides
  @ViewModelScoped
  fun provideCalculateBmrUseCase(): CalculateBmr {
    return CalculateBmr()
  }

  @Provides
  @ViewModelScoped
  fun provideDailyCaloriesRequirementUseCase(calculateBmr: CalculateBmr): DailyCaloriesRequirement {
    return DailyCaloriesRequirement(calculateBmr)
  }


  @Provides
  @ViewModelScoped
  fun provideTrackerDomainUseCases(
    repository: TrackerRepository,
    preferences: Preferences,
    calculateBmr: CalculateBmr,
    dailyCaloriesRequirement: DailyCaloriesRequirement
  ): TrackerDomainUseCases {
    return TrackerDomainUseCases(
      trackFood = TrackFood(repository),
      searchFood = SearchFood(repository),
      getFoodsForDate = GetFoodsForDate(repository),
      deleteTrackedFood = DeleteTrackedFood(repository),
      calculateMealNutrients = CalculateMealNutrients(
        preferences,
        dailyCaloriesRequirement
      )
    )
  }
}