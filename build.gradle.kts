buildscript {
  dependencies {
    classpath(Build.hiltGradlePlugin)
  }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id(Build.androidApplication) version Build.gradleVersion apply false
  id(Build.androidLibrary) version Build.gradleVersion apply false
  id(Kotlin.android) version Kotlin.version apply false
  id(DaggerHilt.hiltGradlePlugin) version DaggerHilt.version apply false
  id(Kotlin.jvm) version Kotlin.jvmVersion apply false
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}