package me.darthwithap.android.calorie_tracker.tracker_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes

@Composable
fun NutrientInfo(
  name: String,
  amount: Int,
  unit: String,
  modifier: Modifier = Modifier,
  amountTextSize: TextUnit = TextSizes.current.big,
  amountColor: Color = MaterialTheme.colors.onBackground,
  unitTextSize: TextUnit = TextSizes.current.base,
  unitColor: Color = MaterialTheme.colors.onBackground,
  nameTextStyle: TextStyle = MaterialTheme.typography.body1
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UnitDisplay(
      amount = amount,
      unit = unit,
      amountTextSize = amountTextSize,
      amountColor = amountColor,
      unitTextSize = unitTextSize,
      unitColor = unitColor
    )
    Text(
      text = name,
      color = MaterialTheme.colors.onBackground,
      style = nameTextStyle,
      fontWeight = FontWeight.Light
    )

  }
}