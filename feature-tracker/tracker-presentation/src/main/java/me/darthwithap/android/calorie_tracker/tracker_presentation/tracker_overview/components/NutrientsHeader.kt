package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core_ui.CarbColor
import me.darthwithap.android.calorie_tracker.core_ui.FatColor
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.ProteinColor
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes
import me.darthwithap.android.calorie_tracker.tracker_presentation.components.UnitDisplay
import me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.TrackerOverviewState

@Composable
fun NutrientsHeader(
  state: TrackerOverviewState,
  modifier: Modifier = Modifier
) {
  val dimens = LocalDimensions.current
  val textSizes = TextSizes.current
  val animatedCalorieCount = animateIntAsState(targetValue = state.totalCalories)

  Column(
    modifier = modifier
      .fillMaxWidth()
      .clip(
        RoundedCornerShape(bottomStart = dimens.xxl, bottomEnd = dimens.xxl)
      )
      .background(MaterialTheme.colors.primary)
      .padding(horizontal = dimens.big, vertical = dimens.large)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      UnitDisplay(
        amount = animatedCalorieCount.value,
        unit = stringResource(id = R.string.kcal),
        amountColor = MaterialTheme.colors.onPrimary,
        amountTextSize = textSizes.xxl,
        unitColor = MaterialTheme.colors.onPrimary,
        unitTextSize = textSizes.base,
        modifier = Modifier.align(Alignment.Bottom)
      )
      Column {
        Text(
          text = stringResource(id = R.string.your_goal), style = MaterialTheme.typography.body2,
          color = MaterialTheme.colors.onPrimary
        )
        UnitDisplay(
          amount = animatedCalorieCount.value,
          unit = stringResource(id = R.string.kcal),
          amountColor = MaterialTheme.colors.onPrimary,
          amountTextSize = textSizes.xxl,
          unitColor = MaterialTheme.colors.onPrimary,
          unitTextSize = textSizes.base
        )
      }
    }
    Spacer(modifier = Modifier.height(dimens.small))
    NutrientsBar(
      carbs = state.totalCarbs,
      protein = state.totalProtein,
      fat = state.totalFat,
      calories = state.totalCalories,
      caloriesGoal = state.caloriesGoal,
      modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)
    )
    Spacer(modifier = Modifier.height(dimens.xl))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      NutrientBarInfo(
        value = state.totalCarbs,
        goal = state.carbsGoal,
        name = stringResource(id = R.string.carbs),
        color = CarbColor,
        modifier = Modifier.size(
          dimens.huge
        )
      )
      NutrientBarInfo(
        value = state.totalProtein,
        goal = state.proteinGoal,
        name = stringResource(id = R.string.protein),
        color = ProteinColor,
        modifier = Modifier.size(
          dimens.huge
        )
      )
      NutrientBarInfo(
        value = state.totalFat,
        goal = state.fatGoal,
        name = stringResource(id = R.string.fat),
        color = FatColor,
        modifier = Modifier.size(
          dimens.huge
        )
      )
    }
  }
}