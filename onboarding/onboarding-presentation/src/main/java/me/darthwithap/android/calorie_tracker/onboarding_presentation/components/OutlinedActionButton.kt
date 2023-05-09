package me.darthwithap.android.calorie_tracker.onboarding_presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
  OutlinedButton(
    onClick = onClick,
    modifier = modifier,
    border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
    shape = RoundedCornerShape(LocalDimensions.current.large),
    colors = ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colors.onPrimary,
    ),
    enabled = isEnabled
  ) {
    Text(
      text = text,
      style = textStyle,
      color = MaterialTheme.colors.primary,
      modifier = Modifier.padding(textPadding)
    )
  }
}