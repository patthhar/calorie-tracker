package me.darthwithap.android.calorie_tracker.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions

@Composable
fun OutlinedActionButton(
  text: String,
  modifier: Modifier = Modifier,
  textPadding: Dp = LocalDimensions.current.xs,
  isEnabled: Boolean = false,
  textStyle: TextStyle = MaterialTheme.typography.button,
  onClick: () -> Unit
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    border = ButtonDefaults.outlinedBorder,
    shape = RoundedCornerShape(LocalDimensions.current.large),
    enabled = isEnabled
  ) {
    Text(
      text = text,
      style = textStyle,
      color = MaterialTheme.colors.onPrimary,
      modifier = Modifier.padding(textPadding)
    )
  }
}