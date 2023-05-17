package me.darthwithap.android.calorie_tracker.tracker_presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes
import me.darthwithap.android.calorie_tracker.tracker_presentation.R
import me.darthwithap.android.calorie_tracker.tracker_presentation.components.NutrientInfo
import me.darthwithap.android.calorie_tracker.tracker_presentation.search.TrackableFoodUiState
import me.darthwithap.android.calorie_tracker.core.R as CoreR

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackableFoodItem(
  trackableFoodUiState: TrackableFoodUiState,
  onClick: () -> Unit,
  onAmountChange: (String) -> Unit,
  onTrack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val dimens = LocalDimensions.current
  val textSizes = TextSizes.current
  val food = trackableFoodUiState.food

  Column(
    modifier = modifier
      .clip(RoundedCornerShape(dimens.regular))
      .padding(dimens.xs)
      .shadow(elevation = dimens.min, shape = RoundedCornerShape(dimens.regular))
      .background(MaterialTheme.colors.surface)
      .clickable { onClick() }
      .padding(end = dimens.medium)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(modifier = Modifier.weight(1f)) {
        Image(
          painter = rememberImagePainter(data = food.imageUrl, builder = {
            crossfade(true)
            error(R.drawable.ic_burger)
            fallback(R.drawable.ic_burger)
          }),
          contentDescription = food.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .size(dimens.huge)
            .clip(RoundedCornerShape(topStart = dimens.regular))
        )
        Spacer(modifier = Modifier.width(dimens.medium))
        Column(modifier = Modifier.align(CenterVertically)) {
          Text(text = food.name, style = MaterialTheme.typography.body1, maxLines = 1)
          Spacer(modifier = Modifier.height(dimens.small))
          Text(
            text = stringResource(id = CoreR.string.kcal_per_100g, food.caloriesPer100gm),
            style = MaterialTheme.typography.body2
          )
        }
      }
      Row {
        NutrientInfo(
          name = stringResource(id = CoreR.string.carbs),
          amount = food.carbsPer100gm,
          unit = stringResource(id = CoreR.string.grams),
          amountTextSize = textSizes.medium,
          unitTextSize = textSizes.small, nameTextStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.width(dimens.small))
        NutrientInfo(
          name = stringResource(id = CoreR.string.protein),
          amount = food.proteinsPer100gm,
          unit = stringResource(id = CoreR.string.grams),
          amountTextSize = textSizes.medium,
          unitTextSize = textSizes.small, nameTextStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.width(dimens.small))
        NutrientInfo(
          name = stringResource(id = CoreR.string.fat),
          amount = food.fatsPer100gm,
          unit = stringResource(id = CoreR.string.grams),
          amountTextSize = textSizes.medium,
          unitTextSize = textSizes.small, nameTextStyle = MaterialTheme.typography.body2
        )
      }
    }
    AnimatedVisibility(visible = trackableFoodUiState.isExpanded) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(dimens.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
      ) {
        Row {
          BasicTextField(
            value = trackableFoodUiState.amount,
            onValueChange = onAmountChange,
            keyboardOptions = KeyboardOptions(
              imeAction = if (trackableFoodUiState.amount.isNotBlank()) {
                ImeAction.Done
              } else ImeAction.Default,
              keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
              onDone = {
                onTrack()
                defaultKeyboardAction(ImeAction.Done)
              }
            ),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
              .border(
                shape = RoundedCornerShape(dimens.regular),
                width = dimens.min,
                color = MaterialTheme.colors.onSurface
              )
              .alignBy(LastBaseline)
              .padding(dimens.medium)
          )
          Spacer(modifier = Modifier.width(dimens.xs))
          Text(
            text = stringResource(id = CoreR.string.grams),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.alignBy(LastBaseline)
          )
        }
        IconButton(onClick = onTrack, enabled = trackableFoodUiState.amount.isNotBlank()) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(id = CoreR.string.track)
          )
        }
      }
    }
  }
}