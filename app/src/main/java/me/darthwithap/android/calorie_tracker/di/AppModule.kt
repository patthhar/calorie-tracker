package me.darthwithap.android.calorie_tracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.darthwithap.android.calorie_tracker.core.data.preferences.DefaultPreferences
import me.darthwithap.android.calorie_tracker.core.domain.preferences.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideSharedPreferences(app: Application): SharedPreferences {
    return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
  }

  @Provides
  @Singleton
  fun providePreferences(sharedPrefs: SharedPreferences): Preferences {
    return DefaultPreferences(sharedPrefs)
  }
}