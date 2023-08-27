package me.darthwithap.android.calorie_tracker.onboarding_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes

@Composable
fun UnitTextField(
  modifier: Modifier = Modifier,
  value: String,
  unit: String,
  textStyle: TextStyle = LocalTextStyle.current.copy(
    color = MaterialTheme.colors.primaryVariant,
    fontSize = TextSizes.current.huge,
    textAlign = TextAlign.Center
  ),
  onValueChange: (String) -> Unit
) {
  val dimens = LocalDimensions.current
  Row(
    modifier = modifier, horizontalArrangement = Arrangement.Center
  ) {
    OutlinedTextField(
      value = value,
      onValueChange = onValueChange,
      textStyle = textStyle,
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
      ),
      singleLine = true,
      modifier = Modifier
        .width(IntrinsicSize.Min)
        .alignBy(LastBaseline)
    )
    Spacer(modifier = Modifier.width(dimens.small))
    Text(text = unit, modifier = Modifier.alignBy(LastBaseline))
  }
}