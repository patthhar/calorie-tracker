package me.darthwithap.android.calorie_tracker.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.darthwithap.android.calorie_tracker.onboarding_domain.usecases.ValidateNutrientPercentages

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {
  @Provides
  @ViewModelScoped
  fun provideValidateNutrientPercentagesUseCase(): ValidateNutrientPercentages {
    return ValidateNutrientPercentages()
  }
}