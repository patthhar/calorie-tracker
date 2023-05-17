package me.darthwithap.android.calorie_tracker.tracker_presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions

@Composable
fun SearchTextField(
  text: String,
  onValueChange: (String) -> Unit,
  onSearch: () -> Unit,
  modifier: Modifier = Modifier,
  hint: String = stringResource(id = R.string.search),
  shouldShowHint: Boolean = false,
  onFocusChange: (FocusState) -> Unit
) {
  val dimens = LocalDimensions.current

  Box(modifier = modifier) {
    BasicTextField(
      value = text,
      onValueChange = onValueChange,
      singleLine = true,
      keyboardActions = KeyboardActions(onSearch = {
        onSearch.invoke()
        //To close the keyboard
        defaultKeyboardAction(ImeAction.Search)
      }),
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
      modifier = Modifier
        .clip(RoundedCornerShape(dimens.regular))
        .padding(dimens.xs)
        .shadow(
          elevation = dimens.xs,
          shape = RoundedCornerShape(dimens.regular)
        )
        .background(MaterialTheme.colors.surface)
        .fillMaxWidth()
        .padding(dimens.medium)
        .padding(end = dimens.medium)
        .onFocusChanged { onFocusChange(it) }
    )
    if (shouldShowHint) {
      Text(
        text = hint,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Light,
        color = Color.LightGray,
        modifier = Modifier
          .align(
            Alignment.CenterStart
          )
          .padding(start = dimens.medium)
      )
    }
    IconButton(onClick = onSearch, modifier = Modifier.align(Alignment.CenterEnd)) {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(id = R.string.search)
      )
    }
  }
}