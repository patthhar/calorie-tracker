package me.darthwithap.android.calorie_tracker.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes

@Composable
fun UnitDisplay(
  amount: Int,
  unit: String,
  modifier: Modifier = Modifier,
  amountTextSize: TextUnit = TextSizes.current.big,
  amountColor: Color = MaterialTheme.colors.onBackground,
  unitTextSize: TextUnit = TextSizes.current.base,
  unitColor: Color = MaterialTheme.colors.onBackground
) {
  val dimens = LocalDimensions.current

  Row(modifier = modifier) {
    Text(
      text = amount.toString(),
      style = MaterialTheme.typography.h2,
      fontSize = amountTextSize,
      color = amountColor,
      modifier = Modifier.alignBy(LastBaseline)
    )
    Spacer(modifier = Modifier.width(dimens.xs))
    Text(
      text = unit,
      style = MaterialTheme.typography.body1,
      fontSize = unitTextSize,
      color = unitColor,
      modifier = Modifier.alignBy(LastBaseline)
    )
  }
}