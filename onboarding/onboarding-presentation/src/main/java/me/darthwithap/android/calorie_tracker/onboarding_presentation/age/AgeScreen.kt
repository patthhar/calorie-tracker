package me.darthwithap.android.calorie_tracker.onboarding_presentation.age

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.OutlinedActionButton
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreen(
  scaffoldState: ScaffoldState,
  viewModel: AgeViewModel = hiltViewModel(),
  onNavigate: (UiEvent.Navigate) -> Unit
) {
  val context = LocalContext.current
  val dimens = LocalDimensions.current

  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collect { event ->
      when (event) {
        is UiEvent.Navigate -> {
          onNavigate(event)
        }
        is UiEvent.ShowSnackBar -> {
          scaffoldState.snackbarHostState.showSnackbar(event.msg.asString(context))
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
        text = stringResource(id = R.string.whats_your_age),
        style = MaterialTheme.typography.h3
      )
      Spacer(modifier = Modifier.height(dimens.medium))
      UnitTextField(
        value = viewModel.age,
        unit = stringResource(id = R.string.years),
        onValueChange = viewModel::onAgeEnter
      )
    }
    OutlinedActionButton(
      modifier = Modifier.align(Alignment.BottomEnd),
      text = stringResource(id = R.string.next),
      isEnabled = true,
    ) {
      viewModel.onNextClick()
    }
  }
}