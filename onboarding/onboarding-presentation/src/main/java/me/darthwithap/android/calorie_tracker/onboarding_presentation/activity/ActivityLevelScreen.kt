package me.darthwithap.android.calorie_tracker.onboarding_presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.domain.models.ActivityLevel
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.OutlinedActionButton
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityLevelScreen(
  viewModel: ActivityLevelViewModel = hiltViewModel(),
  onNextClick: () -> Unit
) {
  val dimens = LocalDimensions.current

  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collect { event ->
      when (event) {
        is UiEvent.NavigateOnSuccess -> {
          onNextClick()
        }
        else -> { /*do nothing*/
        }
      }
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(dimens.medium)
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = stringResource(id = R.string.whats_your_activity_level),
        style = MaterialTheme.typography.h3
      )
      Spacer(modifier = Modifier.height(dimens.medium))
      Row {
        SelectableButton(
          text = stringResource(id = R.string.low),
          color = MaterialTheme.colors.primary,
          selectedTextColor = Color.White,
          isSelected = viewModel.selectedActivity is ActivityLevel.Low,
          textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Medium)
        ) {
          viewModel.onActivityLevelClick(ActivityLevel.Low)
        }
        Spacer(modifier = Modifier.width(dimens.small))
        SelectableButton(
          text = stringResource(id = R.string.medium),
          color = MaterialTheme.colors.primary,
          selectedTextColor = Color.White,
          isSelected = viewModel.selectedActivity is ActivityLevel.Medium,
          textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Medium)
        ) {
          viewModel.onActivityLevelClick(ActivityLevel.Medium)
        }
        Spacer(modifier = Modifier.width(dimens.small))
        SelectableButton(
          text = stringResource(id = R.string.high),
          color = MaterialTheme.colors.primary,
          selectedTextColor = Color.White,
          isSelected = viewModel.selectedActivity is ActivityLevel.High,
          textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Medium)
        ) {
          viewModel.onActivityLevelClick(ActivityLevel.High)
        }
      }
    }
    OutlinedActionButton(
      modifier = Modifier
        .padding(end = dimens.medium, bottom = dimens.medium)
        .align(Alignment.BottomEnd),
      text = stringResource(id = R.string.next),
      isEnabled = true,
    ) {
      viewModel.onNextClick()
    }
  }
}