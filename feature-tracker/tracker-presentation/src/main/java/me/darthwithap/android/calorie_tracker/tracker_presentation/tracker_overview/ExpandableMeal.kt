package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes
import me.darthwithap.android.calorie_tracker.tracker_presentation.components.NutrientInfo
import me.darthwithap.android.calorie_tracker.tracker_presentation.components.UnitDisplay

@Composable
fun ExpandableMeal(
  meal: Meal,
  modifier: Modifier = Modifier,
  onToggleClick: () -> Unit,
  content: @Composable () -> Unit
) {
  val dimens = LocalDimensions.current
  val textSizes = TextSizes.current
  val context = LocalContext.current

  Column(modifier = modifier) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { onToggleClick() }
        .padding(dimens.medium),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(id = meal.drawableRes),
        contentDescription = meal.name.asString(context)
      )
      Spacer(modifier = Modifier.width(dimens.medium))
      Column(modifier = Modifier.weight(1f)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
          Text(text = meal.name.asString(context), style = MaterialTheme.typography.h3)
          Icon(
            imageVector = if (meal.isExpanded) Icons.Default.KeyboardArrowUp
            else Icons.Default.KeyboardArrowDown,
            contentDescription = if (meal.isExpanded) stringResource(id = R.string.collapse)
            else stringResource(
              id = R.string.extend
            )
          )
        }
        Spacer(modifier = Modifier.height(dimens.small))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          UnitDisplay(
            amount = meal.calories,
            unit = stringResource(R.string.kcal),
            amountTextSize = textSizes.xl
          )
          Row {
            NutrientInfo(
              name = stringResource(id = R.string.carbs),
              amount = meal.carbs,
              unit = stringResource(
                id = R.string.grams
              )
            )
            Spacer(modifier = Modifier.width(dimens.small))
            NutrientInfo(
              name = stringResource(id = R.string.protein),
              amount = meal.protein,
              unit = stringResource(
                id = R.string.grams
              )
            )
            Spacer(modifier = Modifier.width(dimens.small))
            NutrientInfo(
              name = stringResource(id = R.string.fat),
              amount = meal.fat,
              unit = stringResource(
                id = R.string.grams
              )
            )
          }
        }
      }
    }
    Spacer(modifier = Modifier.height(dimens.medium))
    AnimatedVisibility(visible = meal.isExpanded) {
      content()
    }
  }
}