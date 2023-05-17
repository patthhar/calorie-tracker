package me.darthwithap.android.calorie_tracker.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes
import me.darthwithap.android.calorie_tracker.tracker_domain.models.TrackedFood
import me.darthwithap.android.calorie_tracker.tracker_presentation.R
import me.darthwithap.android.calorie_tracker.tracker_presentation.components.NutrientInfo
import me.darthwithap.android.calorie_tracker.core.R as CoreR

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodItem(
  trackedFood: TrackedFood,
  onDeleteClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val dimens = LocalDimensions.current
  val textSizes = TextSizes.current

  Row(
    modifier = modifier
      .clip(
        RoundedCornerShape(dimens.regular)
      )
      .padding(dimens.small)
      .shadow(elevation = dimens.min, shape = RoundedCornerShape(dimens.regular))
      .background(MaterialTheme.colors.surface)
      .padding(end = dimens.base)
      .height(dimens.huge),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberImagePainter(data = trackedFood.imageUrl, builder = {
        crossfade(true)
        error(R.drawable.ic_burger)
        fallback(R.drawable.ic_burger)
      }),
      contentDescription = trackedFood.name,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxHeight()
        .aspectRatio(1f)
        .clip(RoundedCornerShape(topStart = dimens.regular, bottomStart = dimens.regular))
    )
    Spacer(modifier = Modifier.width(dimens.base))
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = trackedFood.name,
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
      )
      Spacer(modifier = Modifier.height(dimens.small))
      Text(
        text = stringResource(
          id = CoreR.string.nutrient_info,
          trackedFood.amountInGms,
          trackedFood.calories
        ),
        style = MaterialTheme.typography.body2
      )
    }
    Spacer(modifier = Modifier.width(dimens.medium))
    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.Center
    ) {
      Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = stringResource(id = CoreR.string.delete),
        modifier = Modifier
          .align(Alignment.End)
          .clickable { onDeleteClick() }
      )
      Spacer(modifier = Modifier.height(dimens.xs))
      Row(verticalAlignment = Alignment.CenterVertically) {
        NutrientInfo(
          name = stringResource(id = CoreR.string.carbs),
          amount = trackedFood.carbs,
          unit = stringResource(
            id = CoreR.string.grams
          ),
          amountTextSize = textSizes.medium,
          unitTextSize = textSizes.small,
          nameTextStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.width(dimens.small))
        NutrientInfo(
          name = stringResource(id = CoreR.string.protein),
          amount = trackedFood.protein,
          unit = stringResource(
            id = CoreR.string.grams
          ),
          amountTextSize = textSizes.medium,
          unitTextSize = textSizes.small,
          nameTextStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.width(dimens.small))
        NutrientInfo(
          name = stringResource(id = CoreR.string.fat),
          amount = trackedFood.fats,
          unit = stringResource(
            id = CoreR.string.grams
          ),
          amountTextSize = textSizes.medium,
          unitTextSize = textSizes.small,
          nameTextStyle = MaterialTheme.typography.body2
        )
      }
    }
  }
}