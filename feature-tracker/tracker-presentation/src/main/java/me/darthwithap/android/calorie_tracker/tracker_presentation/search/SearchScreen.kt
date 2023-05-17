package me.darthwithap.android.calorie_tracker.tracker_presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes
import me.darthwithap.android.calorie_tracker.tracker_presentation.search.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
  scaffoldState: ScaffoldState,
  viewModel: SearchViewModel = hiltViewModel(),
  mealName: String,
  dayOfMonth: Int,
  month: Int,
  year: Int,
  onNavigateUp: () -> Unit
) {
  val dimens = LocalDimensions.current
  val textSizes = TextSizes.current
  val context = LocalContext.current
  val state = viewModel.state
  val keyboardController = LocalSoftwareKeyboardController.current

  LaunchedEffect(key1 = keyboardController) {
    viewModel.uiEvent.collect { event ->
      when (event) {
        is UiEvent.ShowSnackBar -> {
          scaffoldState.snackbarHostState.showSnackbar(
            message = event.msg.asString(context)
          )
          keyboardController?.hide()
        }

        is UiEvent.NavigateUp -> onNavigateUp.invoke()
        else -> Unit
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(dimens.medium)
  ) {
    Text(
      text = stringResource(id = R.string.add_meal, mealName),
      style = MaterialTheme.typography.h2
    )
    Spacer(modifier = Modifier.height(dimens.medium))
    SearchTextField(
      text = state.query,
      onValueChange = {
        viewModel.onEvent(SearchEvent.OnQueryChange(it))
      },
      onSearch = { viewModel.onEvent(SearchEvent.OnSearch) },
      onFocusChange = {
        viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
      })
  }
}