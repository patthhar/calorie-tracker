package me.darthwithap.android.calorie_tracker.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions

@Composable
fun SelectableButton(
  modifier: Modifier = Modifier,
  text: String,
  isSelected: Boolean = false,
  color: Color,
  selectedTextColor: Color,
  textStyle: TextStyle = MaterialTheme.typography.button,
  onClick: () -> Unit
) {
  val dimens = LocalDimensions.current
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
      .clip(RoundedCornerShape(dimens.xxl))
      .background(
        color = if (isSelected) color else Color.Transparent,
        shape = RoundedCornerShape(dimens.xxl)
      )
      .clickable { onClick() }
      .border(width = dimens.xs, color = color, shape = RoundedCornerShape(dimens.xxl))
      .padding(dimens.small)
  ) {
    Text(
      modifier = Modifier.padding(dimens.small),
      text = text, style = textStyle,
      color = if (isSelected) selectedTextColor else color,
    )
  }
}