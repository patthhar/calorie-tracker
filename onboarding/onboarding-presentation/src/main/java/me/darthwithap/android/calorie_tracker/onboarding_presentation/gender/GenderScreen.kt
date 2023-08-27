package me.darthwithap.android.calorie_tracker.onboarding_presentation.gender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import me.darthwithap.android.calorie_tracker.core.domain.models.Gender
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.OutlinedActionButton
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.SelectableButton

@Composable
fun GenderScreen(
  viewModel: GenderViewModel = hiltViewModel(),
  onNextClick: () -> Unit
) {
  val dimens = LocalDimensions.current
  val textSizes = TextSizes.current

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
        text = stringResource(id = R.string.whats_your_gender),
        style = MaterialTheme.typography.h3
      )
      Spacer(modifier = Modifier.height(dimens.medium))
      Row {
        SelectableButton(
          text = stringResource(id = R.string.male),
          color = MaterialTheme.colors.primary,
          selectedTextColor = Color.White,
          isSelected = viewModel.selectedGender is Gender.Male,
          textStyle = MaterialTheme.typography.button.copy(
            fontWeight = FontWeight.Medium
          )
        ) {
          viewModel.onGenderClick(Gender.Male)
        }
        Spacer(modifier = Modifier.width(dimens.base))
        SelectableButton(
          text = stringResource(id = R.string.female),
          color = MaterialTheme.colors.primary,
          selectedTextColor = Color.White,
          isSelected = viewModel.selectedGender is Gender.Female,
          textStyle = MaterialTheme.typography.button.copy(
            fontWeight = FontWeight.Medium
          )
        ) {
          viewModel.onGenderClick(Gender.Female)
        }
      }
    }
    OutlinedActionButton(
      modifier = Modifier
        .padding(end = dimens.big, bottom = dimens.big)
        .align(Alignment.BottomEnd),
      text = stringResource(id = R.string.next),
      isEnabled = true,
    ) {
      viewModel.onNextClick()
    }
  }
}