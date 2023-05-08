package me.darthwithap.android.calorie_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.darthwithap.android.calorie_tracker.ui.theme.CalorieTrackerTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CalorieTrackerTheme {
      }
    }
  }
}