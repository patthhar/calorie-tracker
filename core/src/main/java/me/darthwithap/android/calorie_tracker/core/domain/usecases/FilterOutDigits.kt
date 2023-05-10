package me.darthwithap.android.calorie_tracker.core.domain.usecases

class FilterOutDigits {

  operator fun invoke(text: String): String {
    return text.filter { it.isDigit() }
  }

}