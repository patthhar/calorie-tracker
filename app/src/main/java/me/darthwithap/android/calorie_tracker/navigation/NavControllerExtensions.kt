package me.darthwithap.android.calorie_tracker.navigation

import androidx.navigation.NavController
import me.darthwithap.android.calorie_tracker.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
  this.navigate(event.route)
}