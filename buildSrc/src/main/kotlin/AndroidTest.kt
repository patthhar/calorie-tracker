object AndroidTest {
  private const val junitAndroidExtVersion = "1.1.5"
  const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

  private const val espressoVersion = "3.5.1"
  const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"

  const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
  const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Compose.version}"
  const val composeUiManifest = "androidx.compose.ui:ui-test-manifest:${Compose.version}"
}