package me.darthwithap.android.calorie_tracker.tracker_presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import me.darthwithap.android.calorie_tracker.core.R
import me.darthwithap.android.calorie_tracker.core.util.UiEvent
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.tracker_domain.models.MealType
import me.darthwithap.android.calorie_tracker.tracker_presentation.search.components.SearchTextField
import me.darthwithap.android.calorie_tracker.tracker_presentation.search.components.TrackableFoodItem
import java.time.LocalDate

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
      text = stringResource(id = R.string.add_meal, mealName.capitalize(Locale.current)),
      style = MaterialTheme.typography.h2
    )
    Spacer(modifier = Modifier.height(dimens.medium))
    SearchTextField(
      text = state.query,
      onValueChange = {
        viewModel.onEvent(SearchEvent.OnQueryChange(it))
      },
      shouldShowHint = state.isHintVisible,
      onSearch = {
        keyboardController?.hide()
        viewModel.onEvent(SearchEvent.OnSearch)
      },
      onFocusChange = {
        viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
      })
    Spacer(modifier = Modifier.height(dimens.xs))
    LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(state.trackableFoods) { food ->
        TrackableFoodItem(
          trackableFoodUiState = food,
          onTrack = {
            keyboardController?.hide()
            viewModel.onEvent(
              SearchEvent.OnTrackFoodClick(
                food.food,
                MealType.fromString(mealName),
                LocalDate.of(year, month, dayOfMonth)
              )
            )
          },
          onAmountChange = {
            viewModel.onEvent(SearchEvent.OnAmountForFoodChange(it, food.food))
          },
          onClick = { viewModel.onEvent(SearchEvent.OnToggleTrackableFood(food.food)) },
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  }
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    when {
      state.isSearching -> CircularProgressIndicator()
      state.trackableFoods.isEmpty() -> {
        Text(
          text = stringResource(id = R.string.no_results),
          style = MaterialTheme.typography.h4,
          textAlign = TextAlign.Center
        )
      }
    }
  }
}