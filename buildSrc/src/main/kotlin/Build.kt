object Build {

  const val gradleVersion = "7.4.0"
  const val androidApplication = "com.android.application"
  const val androidLibrary = "com.android.library"

  private const val androidBuildToolsVersion = "7.4.0"
  const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

  const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

  private const val hiltGradlePluginVersion = "2.43.1"
  const val hiltGradlePlugin =
    "com.google.dagger:hilt-android-gradle-plugin:$hiltGradlePluginVersion"
}