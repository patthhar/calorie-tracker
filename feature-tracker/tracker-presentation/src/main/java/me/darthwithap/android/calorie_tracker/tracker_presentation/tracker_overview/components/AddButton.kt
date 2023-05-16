package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions

@Composable
fun AddButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colors.primary
) {

  val dimens = LocalDimensions.current
  Row(modifier = modifier
    .clip(RoundedCornerShape(100f))
    .clickable { onClick() }
    .border(
      width = dimens.min, color = color,
      shape = RoundedCornerShape(100f)
    )
    .padding(dimens.medium),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add),
      tint = color
    )
    Spacer(modifier = Modifier.width(dimens.medium))
    Text(text = text, style = MaterialTheme.typography.button, color = color)
  }
}