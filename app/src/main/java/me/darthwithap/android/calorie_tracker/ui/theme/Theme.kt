package me.darthwithap.android.calorie_tracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import me.darthwithap.android.calorie_tracker.core_ui.Dimensions
import me.darthwithap.android.calorie_tracker.core_ui.LocalDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextDimensions
import me.darthwithap.android.calorie_tracker.core_ui.TextSizes

private val DarkColorPalette = darkColors(
  primary = BrightGreen,
  primaryVariant = DarkGreen,
  secondary = Orange,
  background = MediumGray,
  onBackground = TextWhite,
  surface = LightGray,
  onSurface = TextWhite,
  onPrimary = Color.White,
  onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
  primary = BrightGreen,
  primaryVariant = DarkGreen,
  secondary = Orange,
  background = Color.White,
  onBackground = DarkGray,
  surface = Color.White,
  onSurface = DarkGray,
  onPrimary = Color.White,
  onSecondary = Color.White,
)

@Composable
fun CalorieTrackerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }
  CompositionLocalProvider(LocalDimensions provides Dimensions()) {
    CompositionLocalProvider(TextSizes provides TextDimensions()) {
      MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
      )
    }
  }
}