package me.darthwithap.android.calorie_tracker.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import  me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.navigation.Route
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.onboarding_presentation.components.OutlinedActionButton

@Composable
fun WelcomeScreen(
  onNavigate: (UiEvent.Navigate) -> Unit
) {
  val dimens = LocalDimensions.current
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(LocalDimensions.current.base),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = stringResource(id = R.string.welcome),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h1
    )
    Text(
      text = stringResource(id = R.string.welcome_text),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h2
    )
    Spacer(modifier = Modifier.height(dimens.big))
    OutlinedActionButton(
      text = stringResource(id = R.string.next),
      modifier = Modifier.align(Alignment.CenterHorizontally),
      textPadding = dimens.small,
      isEnabled = true
    ) {
      onNavigate(UiEvent.Navigate(Route.Gender))
    }
  }
}