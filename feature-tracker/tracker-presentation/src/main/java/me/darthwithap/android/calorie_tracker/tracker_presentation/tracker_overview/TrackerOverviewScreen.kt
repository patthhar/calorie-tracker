package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components.NutrientsHeader

@Composable
fun TrackerOverviewScreen(
  onNavigate: (UiEvent.Navigate) -> Unit,
  viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
  val dimens = LocalDimensions.current
  val state = viewModel.state
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(bottom = dimens.base)
  ) {
    item {
      NutrientsHeader(state = state)
    }
  }
}